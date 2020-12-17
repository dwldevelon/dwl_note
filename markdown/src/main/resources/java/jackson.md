* jackson反序列化嵌套集合方法
```
 public static <K,VB> Map<K,List<VB>> decode(String json , Class<K> keyClz,Class<VB> valueBeanClass){
    ObjectMapper objectMapper = new ObjectMapper();
    JavaType keyType = objectMapper.getTypeFactory().constructType(keyClz);
    JavaType valueType = objectMapper.getTypeFactory().constructParametricType(List.class, valueBeanClass);
    MapType mapType = objectMapper.getTypeFactory().constructMapType(HashMap.class, keyType, valueType);
    try {
        return objectMapper.readValue(json,mapType);
    } catch (IOException e) {
        log.info("json化错误："+e.getMessage());
    }
    return null;
}
```
* jackson配置格式化
```
ObjectMapper objectMapper = new ObjectMapper();
objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
```
* jackson按类型配置序列化和反序列化
```
SimpleModule module = new SimpleModule();
// spring boot 2.0 版本新增了LocalDateTime类型的序列化器和反序列化器
module.addDeserializer(LocalDateTime.class, JsonDeserializer.class);
module.addSerializer(LocalDateTime.class,JsonSerializer.class);
ObjectMapper objectMapper = new ObjectMapper();
objectMapper.registerModule(module);
```

* jackson配置spring容器中的对象的属性
```
@Bean
public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> {
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimePattern)));
        builder.serializerByType(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern)));
    };
}
```