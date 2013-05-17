package com.mvc.jump;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RenderWrapper {
    private Object content;

    public RenderWrapper(Object content) {
        this.content = content;
    }

    public void render(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        if (content instanceof RenderTemplate) {
            RenderTemplate template = (RenderTemplate) content;
            template.setRealPath(servletContext.getRealPath("/"));
            try {
                template.render(req, resp);
            } catch (Exception e) {
                throw new IllegalStateException("Can't render the page.");
            }
        } else {
            // primitives stuff
        }
    }
}
