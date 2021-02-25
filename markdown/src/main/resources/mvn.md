查看插件信息  
`mvn org.apache.maven.plugins:maven-clean-plugin:help`  

查看goal参数信息  
`mvn org.apache.maven.plugins:maven-clean-plugin:help -Dgoal=help -Ddetail`  

`mvn org.apache.maven.plugins:maven-clean-plugin:help -Dgoal=help -Ddetail=false`  

```mvn
mvn 插件goupId:插件artifactId[:插件version]:插件目标 [-D目标参数1] [-D目标参数2] [-D目标参数n]
mvn 插件前缀:插件目标  [-D目标参数1] [-D目标参数2] [-D目标参数n]
```  

`mvn org.apache.maven.plugins:maven-surefire-plugin:help -Dgoal=test -Ddetail=true`  

`mvn org.apache.maven.plugins:maven-surefire-plugin:test`  
`mvn org.apache.maven.plugins:maven-surefire-plugin:test -Dmaven.test.skip=true` 

maven插件传参的两种方式:
一种通过-D后面跟用户属性的方式给用户传参，还有一种方式，在pom.xml中properties的用户自定义属性中进行配置  


`mvn help:describe -Dplugin=org.apache.maven.plugins:maven-surefire-plugin -Dgoal=test -Ddetail`  


maven内置绑定
clean生命周期阶段与插件绑定关系 

|生命周期阶段	|插件:目标|
|----|----|
|pre-clean||	
|clean	|maven-clean-plugin:clean|
|post-clean||	

clean周期中只有clean阶段默认绑定了maven-clean-plugin插件的clean目标。maven-clean-plugin插件的clean目标作用就是删除项目的输出目录。

default生命周期阶段与插件绑定关系
default生命周期中有23个阶段，我只列出有默认绑定的，其他的没有列出的没有绑定任何插件，因此没有任何实际的行为。

|生命周期阶段|	插件:目标|	执行任务|
|----|----|----|
|process-resources|	maven-resources-plugin:resources|	复制主资源文件至主输出目录|
|compile|	maven-compiler-plugin:compile|	编译主代码至主输出目录|
|process-test-resources|	maven-resources-plugin:testResources|	复制测试资源文件至测试输出目录|
|test-compile|	maven-compiler-plugin:testCompile|	编译测试代码至测试输出目录|
|test|	maven-surefile-plugin:test|	执行测试用例|
|package|	maven-jar-plugin:jar|	创建项目jar包|
|install|	maven-install-plugin:install|	将输出构件安装到本地仓库|
|deploy|	maven-deploy-plugin:deploy|	将输出的构件部署到远程仓库|


site生命周期阶段与插件绑定关系  

|生命周期阶段|	插件:目标|
|----|----|
|pre-site||	
|site	|maven-site-plugin:site|
|post-site	||
|site-deploy|	maven-site-plugin:deploy|  


maven生命周期绑定插件  
```maven
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-source-plugin</artifactId>
            <version>3.2.0</version>
            <executions>
                <!-- 使用插件需要执行的任务 -->
                <execution>
                    <!-- 任务id -->
                    <id>attach-source</id>
                    <!-- 任务中插件的目标，可以指定多个 -->
                    <goals>
                        <goal>jar-no-fork</goal>
                    </goals>
                    <!-- 绑定的阶段 -->
                    <phase>verify</phase>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```   
```maven
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-clean-plugin</artifactId>
    <version>2.5</version>
    <executions>
        <!-- 使用插件需要执行的任务 -->
        <execution>
            <id>clean-target</id>
            <!-- 任务中插件的目标，可以指定多个 -->
            <goals>
                <goal>clean</goal>
            </goals>
            <!-- 绑定的阶段 -->
            <phase>validate</phase>
        </execution>
    </executions>
</plugin>
```
maven插件参数配置  
```git
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.12.4</version>
            <!-- 插件参数配置，对插件中所有的目标起效 -->
            <configuration>
                <skip>true</skip>
            </configuration>
        </plugin>
    </plugins>
</build>
```

maven跳过测试的几种方法:
1. mvn -Dmaven.test.skip=tue
2. properties中配置<maven.test.skip>true</maven.test.skip>
3. build中配置插件参数的方式 


```git
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.12.4</version>
            <executions>
                <execution>
                    <goals>
                        <goal>test</goal>
                        <goal>help</goal>
                    </goals>
                    <phase>pre-clean</phase>
                    <!-- 这个地方配置只对当前任务有效 -->
                    <configuration>
                        <skip>true</skip>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

[maven插件官网](http://maven.apache.org/plugins/)

maven依赖仓库配置
```
<repositories>
    <repository>
        <id>maven-nexus</id>
        <url>http://localhost:8081/repository/maven-public/</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

maven插件仓库配置
```git
<pluginRepositories>
    <pluginRepository>
        <id>myplugin-repository</id>
        <url>http://repo1.maven.org/maven2/</url>
        <releases>
            <enabled>true</enabled>
        </releases>
    </pluginRepository>
</pluginRepositories>
```

在pom.xml中配置插件的时候，如果是官方的插件，可以省略groupId。

`mvn help:effective-pom`  


在maven依赖情况下dependencyManagement的优先级高于 dependencies，
版本仲裁的版本号优先级高于依赖传递。

