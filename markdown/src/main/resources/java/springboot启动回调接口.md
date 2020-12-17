

| 监听器 | 配置方法 | 执行时间 |
| --- | --- | --- |
| ApplicationContextInitializer |  META-INF/spring.factories |  IOC容器初始化 |
| SpringApplicationRunListener | META-INF/spring.factories | spring启动过程多次执行 |
| ApplicationRunner | IOC容器 | 容器启动完成后被回调 |
| CommandLineRunner | IOC容器 | ApplicationRunner执行完成后被执行 | 

