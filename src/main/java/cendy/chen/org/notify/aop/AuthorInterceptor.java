package cendy.chen.org.notify.aop;

import cendy.chen.org.notify.annotation.Anonymous;
import cendy.chen.org.notify.result.RestfulErrorCode;
import cendy.chen.org.notify.exception.RestfulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 白名单校验
 */
public class AuthorInterceptor extends HandlerInterceptorAdapter {

    @Value("#{'${white.list}'.split(',')}")
    private List<String> whiteList;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.getBeanType().isAnnotationPresent(Anonymous.class)) {
                return true;
            }
        }
        String clientIp = getIpAddress(request);
        if (!whiteList.contains(clientIp)) {
            throw new RestfulException("client ip: " + clientIp + " not in white list", RestfulErrorCode.AUTHOR_ERROR);
        }
        return super.preHandle(request, response, handler);
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}