package com.mvc.jump;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RenderWrapper {
    private Object content;

    public RenderWrapper(Object content) {
        this.content = content;
    }

    public String getRenderingContent() {
        return content.toString();
    }

    public void render(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (content instanceof RenderTemplate) {
            RenderTemplate template = (RenderTemplate) content;
            template.setRealPath(servletContext.getRealPath("/"));
            template.render(req, resp);
        }
    }
}
