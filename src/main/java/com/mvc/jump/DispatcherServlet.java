package com.mvc.jump;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class.getName());
    private JumpContainer jumpContainer;
    private UrlMapper urlMapper;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.jumpContainer = new JumpContainerFactory().createContainer(new Config(servletConfig));
        this.urlMapper = new UrlMapper();

        List<Object> objects = jumpContainer.loadAllBeans();
        for (Object object : objects) {
            urlMapper.map(object);
        }
        super.init(servletConfig);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            MethodWrapper method = urlMapper.findMethod(req.getServletPath());
            logger.info("get method: " + method);
            if (method != null) {
                RenderWrapper renderWrapper = method.invoke(req.getServletPath());
                renderWrapper.render(getServletContext(), req, resp);
                logger.info("Rendered for path: " + req.getPathInfo());
            } else {
                StaticFileManager staticFileManager = new StaticFileManager();
                staticFileManager.sendFile(getServletContext(), req, resp);
            }
        } catch (Exception e) {
            resp.sendError(403);
        }
    }
}
