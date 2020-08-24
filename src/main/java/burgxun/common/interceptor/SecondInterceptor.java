package burgxun.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SecondInterceptor
 * @Auther burgxun
 * @Description:
 * @Date 2020/06/16 16:12
 **/
public class SecondInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(SecondInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("【SecondInterceptor】-preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("【SecondInterceptor】-postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("【SecondInterceptor】-afterCompletion");
    }
}
