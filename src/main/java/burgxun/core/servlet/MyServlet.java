package burgxun.core.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MyServlet
 * @Auther burgxun
 * @Description:
 * @Date 2020/06/16 17:15
 **/
public class MyServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(MyServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("【MyServlet】-doGet:uri:" + req.getRequestURI());
        //super.doGet(req, resp);
        resp.getWriter().append("this is my servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("【MyServlet】-doPost:uri:" + req.getRequestURI());
        super.doPost(req, resp);
    }
}
