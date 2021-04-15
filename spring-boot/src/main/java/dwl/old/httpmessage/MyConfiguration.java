package dwl.old.httpmessage;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author wenlong.ding
 * @date 2021/2/1 13:41
 */
@Configuration
public class MyConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0,new MyHttpMessageConverter());
    }
}
