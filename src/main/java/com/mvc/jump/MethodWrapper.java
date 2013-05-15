package com.mvc.jump;

import java.lang.reflect.Method;

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

