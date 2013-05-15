package com.mvc.jump;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RenderTemplate {
    void setRealPath(String path);

    void render(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
