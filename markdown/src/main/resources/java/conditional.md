```
@ConditionalOnClass(KafkaTemplate.class)
这个注解通俗的说就是Spring工程中引用了KafkaTemplate 才会构建这个bean  
就是说只有在classpath下能找到kafkaTemplate类才会构建这个bean
```
```
@ConditionalOnBean（仅仅在当前spring上下文中存在某个对象时，才会实例化一个Bean）
@ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
@ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
@ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
@ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
@ConditionalOnNotWebApplication（不是web应用）
```