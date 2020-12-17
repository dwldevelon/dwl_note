package dwl.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class TestUtils {
    private static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void handelResp(MvcResult mvcResult) throws UnsupportedEncodingException {

        MockHttpServletRequest request = mvcResult.getRequest();

        request.setCharacterEncoding("UTF-8");
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String,String> requestHeaders = new HashMap<>();
        if(headerNames.hasMoreElements()){
            String requestHeaderName = headerNames.nextElement();
            String requestHeaderValue = request.getHeader(requestHeaderName);
            requestHeaders.put(requestHeaderName,requestHeaderValue);
        }
        Map<String,Object> requestInfo = new LinkedHashMap<>();
        requestInfo.put("requestUri",request.getRequestURI());
        requestInfo.put("requestHeaders",requestHeaders);
        requestInfo.put("requestParameters",request.getParameterMap());
        requestInfo.put("requestBody",request.getContentAsString());

        MockHttpServletResponse response = mvcResult.getResponse();
        Map<String,String> headers = response.getHeaderNames().stream().collect(Collectors.toMap(Function.identity(), response::getHeader));
        Map<String,Object> responseInfo = new LinkedHashMap<>(4);
        responseInfo.put("responseStatus", response.getStatus());
        responseInfo.put("headers",headers);
        responseInfo.put("responseBody",response.getContentAsString());
        try {
            log.info("{}",OBJECT_MAPPER.writeValueAsString(requestInfo));
            log.info("{}", OBJECT_MAPPER.writeValueAsString(responseInfo));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
