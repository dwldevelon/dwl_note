package dwl.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dwl.exception.ServiceException;
import dwl.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @author wenlong.ding
 * @date 2020/12/17 10:30
 */
@Slf4j
public class DwlMock {

    private MockMvc mockMvc;
    /**
     * url对应的Controller类和方法信息
     */
    private Map<String, HandlerMethod> urlMapControllerMap;
    /**
     * url对应请求方法信息,一个url可能有多个请求方式
     */
    private Map<String,List<RequestMethod>> urlMapRequestMethodMap;
    private WebApplicationContext webApplicationContext;
    private Set<Class> skipInvokeParameterList;
    private ObjectMapper objectMapper;

    public DwlMock(WebApplicationContext webApplicationContext){
        this.webApplicationContext = webApplicationContext;
        init();
    }

    public static DwlMock of(WebApplicationContext webApplicationContext) {
        return new DwlMock(webApplicationContext);
    }

    public void init() {
        urlMapControllerMap = new HashMap<>();
        urlMapRequestMethodMap = new HashMap<>();
        skipInvokeParameterList = new HashSet<>();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        RequestMappingHandlerMapping mapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            HandlerMethod handlerMethod = map.get(info);
            //获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            // 这里可获取请求方式 Get,Post等等
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            for (String url : patterns) {
                urlMapControllerMap.put(url, handlerMethod);
                urlMapRequestMethodMap.put(url,new ArrayList<>(methods));
            }
        }
        skipInvokeParameterList.add(HttpServletRequest.class);
        skipInvokeParameterList.add(HttpServletResponse.class);
        objectMapper = new ObjectMapper();
    }

    public void test(String url,Object ... parameters) {

        HandlerMethod handlerMethod = urlMapControllerMap.get(url);
        Assert.notNull(handlerMethod, String.format("url[%s] 没有对应的handler", url));

        List<RequestMethod> requestMethods = urlMapRequestMethodMap.get(url);
        RequestMethod requestMethod = requestMethods.get(0);

        MockHttpServletRequestBuilder request ;
        switch (requestMethod){
            case POST:
                request = MockMvcRequestBuilders.post(url);
                break;
            case HEAD:
                request = MockMvcRequestBuilders.head(url);
                break;
            case PUT:
                request = MockMvcRequestBuilders.put(url);
                break;
            case PATCH:
                request = MockMvcRequestBuilders.patch(url);
                break;
            case DELETE:
                request = MockMvcRequestBuilders.delete(url);
                break;
            case OPTIONS:
                request = MockMvcRequestBuilders.options(url);
                break;
            default:
                request = MockMvcRequestBuilders.get(url);
                break;
        }
        Method method = handlerMethod.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Parameter[] ps = method.getParameters();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = u.getParameterNames(method);
        if(parameterNames == null){
            throw new ServiceException("jdk 8 才支持");
        }
        for (int i = 0; i < ps.length; i++) {
            Parameter p = ps[i];
            if(skipInvokeParameterList.contains(parameterTypes[i])){
                continue;
            }

            Annotation[] annotations = p.getAnnotations();
            boolean hasRequestBody  =  false;
            for (Annotation annotation : annotations) {
                if(RequestBody.class.isAssignableFrom(annotation.getClass())){
                    hasRequestBody = true;
                    break;
                }
            }
            if(hasRequestBody){
                try {
                    request.content(parameters[i] instanceof String ? (String) parameters[i] : objectMapper.writeValueAsString(parameters[i]));
                } catch (JsonProcessingException e) {
                    throw new ServiceException("json 异常");
                }
                request.contentType(MediaType.APPLICATION_JSON);
                break;
            }

            request.param(parameterNames[i],String.valueOf(parameters[i]));
        }
        try {
            MvcResult testResult = mockMvc.perform(request).andReturn();
            TestUtils.handelResp(testResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
