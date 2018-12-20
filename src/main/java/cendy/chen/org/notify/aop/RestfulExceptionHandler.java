package cendy.chen.org.notify.aop;

import cendy.chen.org.notify.result.RestfulErrorCode;
import cendy.chen.org.notify.result.Restfulesponse;
import cendy.chen.org.notify.exception.RestfulException;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestfulExceptionHandler {

    @ExceptionHandler(RestfulException.class)
    public ResponseEntity<Restfulesponse> handler(HttpServletRequest request, RestfulException e) {
        try {
            return new ResponseEntity<>(Restfulesponse.error(e.getErrorCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WxErrorException.class)
    public ResponseEntity<Restfulesponse> handler(HttpServletRequest request, WxErrorException e) {
        return new ResponseEntity<>(Restfulesponse.error(RestfulErrorCode.WECHAT_ERROR, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
