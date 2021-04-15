package dwl.WebMvcConfigurer.configureContentNegotiation.contentNegotiation;

import dwl.WebMvcConfigurer.configurePathMatch.CommonConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * //        spring.mvc.contentnegotiation.favor-path-extension: true //偏好使用路径扩展名,如content.json
 * //        spring.mvc.pathmatch.use-registered-suffix-pattern: true //后缀只能是已注册扩展名,content.xx无效
 * @author wenlong.ding
 * @date 2021/4/8 14:40
 */
@SpringBootApplication
@Slf4j
public class MainApplication extends CommonConfig {

    public static void main(String[] args) {
        MAP.put("spring.mvc.contentnegotiation.favor-path-extension", true);
        MAP.put("spring.mvc.pathmatch.use-registered-suffix-pattern", true);
        wrapper(()->{
            String resp = REST_TEMPLATE.getForObject(HOST + "/test.json", String.class);
            log.info("resp:{}",resp);
            resp = REST_TEMPLATE.getForObject(HOST + "/test.xml", String.class);
            log.info("resp2:{}",resp);
        });
    }

    @Data
    private static class Person{private String name,addr;}

    @Configuration
    static class TestConfig implements WebMvcConfigurer{
        @Override
        public void configurePathMatch(PathMatchConfigurer configurer) {

        }
    }

    @RestController
    static class TestController{
        @GetMapping("/test")
        public Person test(){
            Person person = new Person();
            person.setName("ding");
            person.setAddr("cd");
            return person;
        }
    }
}
