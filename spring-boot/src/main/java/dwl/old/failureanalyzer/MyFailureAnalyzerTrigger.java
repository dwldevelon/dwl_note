package dwl.old.failureanalyzer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 触发一个错误验证MyFailureAnalyzer
 * @author wenlong.ding
 * @date 2021/1/26 16:16
 */
@Configuration
@ConditionalOnProperty(prefix = "my-failure-analyzer.trigger",name = "enable",havingValue = "true")
public class MyFailureAnalyzerTrigger {

    @Bean
    public String foo(){
        throw new FailureAnalyzerException("模拟一个错误");
    }

}
