package dwl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author wenlong.ding
 * @date 2021/4/8 10:42
 */
public class CommonConfig {
    protected static ConfigurableApplicationContext CTX;
    protected static RestTemplate REST_TEMPLATE;
    protected static String PORT;
    protected static String HOST;

    protected static Map<String, Object> MAP = new HashMap<>();

    public static void wrapper(Runnable runnable) {
        try {
            Class clazz = null;
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                Optional<StackTraceElement> any = Arrays.stream(e.getStackTrace()).filter(ee -> "main".equals(ee.getMethodName())).findAny();
                clazz = Class.forName(any.get().getClassName());
            }
            SpringApplicationBuilder builder = new SpringApplicationBuilder(clazz);
            builder.properties(MAP);
            SpringApplication build = builder.build();
            CTX = build.run();

            PORT = CTX.getEnvironment().resolvePlaceholders("${server.port:8080}");
            REST_TEMPLATE = CTX.getBean(RestTemplate.class);
            HOST = String.format("http://localhost:%s", PORT);
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CTX.stop();
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
