package dwl.WebMvcConfigurer.configurePathMatch.setUseTrailingSlashMatch;

import dwl.WebMvcConfigurer.configurePathMatch.CommonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wenlong.ding
 * @date 2021/4/8 10:26
 */
@SpringBootApplication
@Slf4j
public class MainApplication extends CommonConfig {


    public static void main(String[] args) {
        MAP.put("server.port", "8081");
        wrapper(()->{
            String resp = REST_TEMPLATE.getForObject(HOST + "/test", String.class);
            log.info("rest:{}",resp);
            resp = REST_TEMPLATE.getForObject(HOST + "/test/", String.class);
            log.info("rest2:{}",resp);
        });
    }

    @Configuration
    @RestController
    class TestController implements WebMvcConfigurer{
        @Override
        public void configurePathMatch(PathMatchConfigurer configurer) {
            //使用PathMatchConfigurer.setUseTrailingSlashMatch(Boolean trailingSlashMatch)设置是否使用尾随斜线匹配。若设置为true，则路径/xx和/xx/等效，Spring MVC下默认是开启的，需关闭设置为false。
            configurer.setUseTrailingSlashMatch(true);
        }
        @GetMapping("/test")
        public String aa(){
            return "success";
        }

    }
}
