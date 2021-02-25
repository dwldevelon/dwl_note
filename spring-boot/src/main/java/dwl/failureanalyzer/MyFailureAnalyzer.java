package dwl.failureanalyzer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;

/**


 1.1。创建自己的FailureAnalyzer
 FailureAnalyzer是在启动时拦截异常并将其转换为人类可读消息的好方法，并包装在内FailureAnalysis。Spring Boot为与应用程序上下文相关的异常，JSR-303验证等提供了这种分析器。您也可以创建自己的。

 AbstractFailureAnalyzer是一个方便的扩展，FailureAnalyzer它检查要处理的异常中是否存在指定的异常类型。您可以对此进行扩展，以便您的实现只有在实际出现异常时才有机会处理该异常。如果由于某种原因无法处理该异常，请返回null以使另一个实现有机会处理该异常。

 FailureAnalyzer实施必须在中注册META-INF/spring.factories。以下示例注册ProjectConstraintViolationFailureAnalyzer：

 org.springframework.boot.diagnostics.FailureAnalyzer=\
 com.example.ProjectConstraintViolationFailureAnalyzer

 如果您需要访问BeanFactory或Environment，则FailureAnalyzer可以分别实现BeanFactoryAware或EnvironmentAware。

 触发器

 *
 *
 * @author wenlong.ding
 * @date 2021/1/26 16:03
 */
@Slf4j
public class MyFailureAnalyzer extends AbstractFailureAnalyzer<FailureAnalyzerException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, FailureAnalyzerException cause) {
        log.info("MyFailureAnalyzer 捕获到异常：" + rootFailure.getMessage());
        log.info("MyFailureAnalyzer 捕获到异常：" + cause.getMessage());
        return new FailureAnalysis("这是MyFailureAnalyzer 的 description","这是MyFailureAnalyzer 的 action",cause);
    }
}
