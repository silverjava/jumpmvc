package com.mvc.jump;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspTemplate implements RenderTemplate {
    private final String jspPage;

    public JspTemplate(String jspPage) {
        this.jspPage = jspPage;
    }

    @Override
    public void setRealPath(String path) {
    }

    @Override
    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getRequestDispatcher(jspPage).forward(req, resp);
    }
}
