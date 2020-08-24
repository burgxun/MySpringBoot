package burgxun.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName MyFilter
 * @Auther burgxun
 * @Description: 过滤器
 * @Date 2020/06/16 15:53
 **/
public class MyFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("【MyFilter】-init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("【MyFilter】-doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("【MyFilter】-destroy");
    }
}
