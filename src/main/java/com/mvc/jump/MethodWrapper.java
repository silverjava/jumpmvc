package com.mvc.jump;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodWrapper {
    private Method method;
    private Object host;
    private URLMatcher urlMatcher;

    public MethodWrapper(URLMatcher urlMatcher, Method method, Object host) {
        this.urlMatcher = urlMatcher;
        this.method = method;
        this.host = host;
    }

    public RenderWrapper invoke(String contextUrl) {
        try {
            String[] params = urlMatcher.getParams(contextUrl);
            return new RenderWrapper(method.invoke(host, (Object[]) params));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}

