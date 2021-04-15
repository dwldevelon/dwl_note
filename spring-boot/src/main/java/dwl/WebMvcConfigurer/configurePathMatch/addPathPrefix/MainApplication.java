package dwl.WebMvcConfigurer.configurePathMatch.addPathPrefix;

import dwl.WebMvcConfigurer.configurePathMatch.CommonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wenlong.ding
 * @date 2021/4/8 10:26
 */
@SpringBootApplication(scanBasePackageClasses = MainApplication.class)
@Slf4j
public class MainApplication extends CommonConfig {


    public static void main(String[] args) {
        wrapper(()->{
            String resp = REST_TEMPLATE.getForObject(HOST + "/api/test", String.class);
            log.info("rest:{}", resp);
        });
    }


    @Configuration
    static class TestConfig implements WebMvcConfigurer{
        @Override
        public void configurePathMatch(PathMatchConfigurer configurer) {
            //可以通过PathMatchConfigurer.addPathPrefix(String prefix, Predicate<Class<?>> predicate)来设置路径前缀。
            //prefix设置路径的前缀，predicate设置匹配起效的控制器类型，本例为对@RestController有效：

//            configurer.addPathPrefix("/api", o->true);
            configurer.addPathPrefix("/api", HandlerTypePredicate.forAnnotation(RestController.class));
        }
    }
    @RestController
    static class TestController{
        @GetMapping("/test")
        public String aa(){
            return "successfff";
        }
    }

}
