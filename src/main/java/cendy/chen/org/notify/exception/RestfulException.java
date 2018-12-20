package cendy.chen.org.notify.exception;

/**
 * controller 接口通用异常
 */
public class RestfulException extends RuntimeException {

    private Integer errorCode;

    public RestfulException() {
    }

    public RestfulException(String message) {
        super(message);
    }

    public RestfulException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
