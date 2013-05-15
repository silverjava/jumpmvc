package com.mvc.jump;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

import static com.google.common.collect.Maps.newHashMap;

public class FreeMarkerTemplate implements RenderTemplate {
    private Object model;
    private String path;
    private String realPath;

    public FreeMarkerTemplate(String path) {
        this.path = path;
    }

    public FreeMarkerTemplate(String path, Object model) {
        this(path);
        this.model = model;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(realPath + "/WEB-INF"));
        Template template = cfg.getTemplate(path);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        template.process(model, resp.getWriter());
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }
}
