package com.mvc.jump;

public class RenderTemplateHelper {
    public static RenderTemplate freemarker(String path, Object model) {
        return new FreeMarkerTemplate(path, model);
    }

    public static RenderTemplate jsp(String path) {
        return new JspTemplate(path);
    }
}
