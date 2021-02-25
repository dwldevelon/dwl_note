package dwl.failureanalyzer;

/**
 * @author wenlong.ding
 * @date 2021/1/26 16:08
 */
public class FailureAnalyzerException extends RuntimeException {
    public FailureAnalyzerException() {
    }

    public FailureAnalyzerException(String message) {
        super(message);
    }

    public FailureAnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailureAnalyzerException(Throwable cause) {
        super(cause);
    }

    public FailureAnalyzerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
