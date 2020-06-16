package burgxun.core.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName ContextListenerServlet
 * @Auther burgxun
 * @Description:
 * @Date 2020/06/16 18:25
 **/
public class ContextListenerServlet implements ServletContextListener {
    private Logger logger= LoggerFactory.getLogger(ContextListenerServlet.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      logger.info("【ContextListenerServlet】-contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("【ContextListenerServlet】-contextDestroyed");
    }
}
