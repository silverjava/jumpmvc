package com.mvc.example;

import com.mvc.jump.*;
import static com.mvc.jump.RenderTemplateHelper.*;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class HelloAction {

    @URLMapping(url = "/hello")
    public RenderTemplate hello() {
        return freemarker("/jsp/hello.ftl");
    }

    @URLMapping(url = "/hellojsp")
    public RenderTemplate helloJsp() {
        return jsp("WEB-INF/jsp/hello.jsp");
    }

    @URLMapping(url = "/hellop/$1")
    public RenderTemplate helloWithParameter(String name) {
        Map<String, Object> model = newHashMap();
        model.put("name", name);
        return freemarker("/jsp/hellop.ftl", model);
    }

    @URLMapping(url = "/hello/$1/$2")
    public RenderTemplate helloWithParameters(String name1, String name2) {
        Map<String, Object> model = newHashMap();
        model.put("name1", name1);
        model.put("name2", name2);
        return freemarker("/jsp/hellops.ftl", model);
    }

    @URLMapping(url = "/signin")
    public RenderTemplate signin() {
        return freemarker("/jsp/signin.ftl");
    }

    @URLMapping(url = "/signin", method = "POST")
    public RenderTemplate signinDone(SignForm form) {
        return freemarker("/jsp/success.ftl");
    }
}
