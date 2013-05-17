package com.mvc.jump;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DispatcherServlet extends HttpServlet {
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
        MethodWrapper method = urlMapper.findMethod(req.getServletPath(), req.getMethod());
        if (method != null) {
            RenderWrapper renderWrapper = null;
            if (req.getMethod().toUpperCase().equals("GET")) {
                renderWrapper = method.invoke(req.getServletPath());
            }

            if (req.getMethod().equals("POST")) {
                renderWrapper = method.invoke(req.getParameterMap());
            }

            if (renderWrapper != null) {
                renderWrapper.render(getServletContext(), req, resp);
            }
        } else {
            StaticFileManager staticFileManager = new StaticFileManager();
            staticFileManager.sendFile(getServletContext(), req, resp);
        }
    }
}
