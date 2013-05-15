package com.mvc.example;

import com.mvc.jump.URLMapping;

public class BlogAction {

    @URLMapping(url = "/hello")
    public String hello() {
        return "Hello";
    }
}
