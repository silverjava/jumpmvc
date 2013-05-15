package com.mvc.jump;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.google.common.collect.Maps.newHashMap;

public class UrlMapper {
    private Map<URLMatcher, MethodWrapper> methods = newHashMap();

    public void map(Object host) {
        List<Method> methodList = Arrays.asList(host.getClass().getDeclaredMethods());
        ImmutableList<Method> urlMappingMethods = FluentIterable.from(methodList).filter(new Predicate<Method>() {
            @Override
            public boolean apply(Method method) {
                return method.isAnnotationPresent(URLMapping.class);
            }
        }).toList();

        for (Method method : urlMappingMethods) {
            URLMapping urlMappingAnnotation = method.getDeclaredAnnotation(URLMapping.class);
            String url = urlMappingAnnotation.url();
            URLMatcher urlMatcher = new URLMatcher(url);
            methods.put(urlMatcher, new MethodWrapper(urlMatcher, method, host));
        }
    }

    public MethodWrapper findMethod(final String url) throws NoSuchMethodException {
        URLMatcher matcher = FluentIterable.from(methods.keySet()).firstMatch(new Predicate<URLMatcher>() {
            @Override
            public boolean apply(URLMatcher input) {
                return input.match(url);
            }
        }).orNull();

        if (matcher == null) {
            return null;
        }
        return methods.get(matcher);
    }
}
