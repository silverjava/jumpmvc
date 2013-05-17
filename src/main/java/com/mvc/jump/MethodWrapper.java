package com.mvc.jump;

import com.mvc.example.SignForm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

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
            return invokeMethod(urlMatcher.getParams(contextUrl));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    private RenderWrapper invokeMethod(Object[] params) throws IllegalAccessException, InvocationTargetException {
        return new RenderWrapper(method.invoke(host, params));
    }

    public RenderWrapper invoke(Map parameterMap) {
        Parameter[] genericParameters = method.getParameters();
        List<Object> params = newArrayList();

        for (Parameter genericParam : genericParameters) {
            String name = genericParam.isAnnotationPresent(RequestParam.class) ?
                    genericParam.getAnnotation(RequestParam.class).value() : genericParam.getName();
            BeanMapper mapper = new BeanMapper(genericParam.getType(), name);
            System.out.println(genericParam.getType());
            System.out.println(name);
            params.add(mapper.createAndMap(parameterMap));
        }

        try {
            return invokeMethod(params.toArray());
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}

