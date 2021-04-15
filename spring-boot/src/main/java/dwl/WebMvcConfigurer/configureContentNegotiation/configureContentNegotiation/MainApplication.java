package dwl.WebMvcConfigurer.configureContentNegotiation.configureContentNegotiation;

import dwl.WebMvcConfigurer.configurePathMatch.CommonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;

/**
 * @author wenlong.ding
 * @date 2021/4/8 15:00
 */
@SpringBootApplication
@Slf4j
public class MainApplication  extends CommonConfig {
    public static void main(String[] args) {
        String resp = REST_TEMPLATE.getForObject(HOST + "/test", String.class);
        log.info("rest:{}",resp);
    }
    @Configuration
    static class TestConfig implements WebMvcConfigurer{
        @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.mediaType("ap", new MediaType("application","another-person", Charset.defaultCharset()));
        }
    }
    @RestController
    static class TestController{
        @GetMapping("/test")
        public Object test(){
            return "success";
        }
    }
}
