此模块主要是对MockMvc再次封装  

常规方法可能是这样进行测试的
```
    @Autowired
    private org.springframework.web.context.WebApplicationContext context;

MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
Map<String,String> map = new HashMap<>();
map.put("id","123");
map.put("name","wen");
String uri = "/test1";
MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
        .param("id","456")
        .param("name","long")
        .content(new ObjectMapper().writeValueAsString(map))
        .accept(MediaType.APPLICATION_JSON))
        .andReturn();
log.info("dispatcherServlet:{}",uri);
TestUtils.handlResp(mvcResult);
```

使用此模块可以像以下代码这样简单。

```
@Autowired
WebApplicationContext webApplicationContext;
DwlMock.of(webApplicationContext).test("/test1","ding");
```

dwl.test.DwlMock#of(WebApplicationContext),静态创建一个测试对象，并初始化测试环境
dwl.test.DwlMock#test(..) ,第一个参数为请求的url，必须在当前项目正确定义。后面的动态参数为请求Controller的方法需要的参数。HttpServletRequest和HttpServletResponse只需要传null就可已了。目前不知此路径参数。实现原理也很简单，从WebApplicationContext中获取Request Mapping Handler的url和请求请求方法，进行匹配。  
DwlMock可以自动识别http请求方式，Get或者Post，自动配置参数。如果是对象的话，也还没实现。 

###### 待优化

* 路径参数
* 对象包装的参数
* 实现通过对象.方法调用，和url不同，不过好像挺难的。