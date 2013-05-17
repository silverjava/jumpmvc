package com.mvc.jump;

import com.google.common.base.CaseFormat;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class BeanMapper {
    private Class<?> clazz;
    private String name;

    public BeanMapper(Class<?> clazz, String name) {
        this.clazz = clazz;
        this.name = name;
    }

    public Object createAndMap(Map parameterMap) {
        if (isSimpleType(clazz)) {
            return createForPrimitive(parameterMap);
        }

        try {
            return createAndMapForComplexType(parameterMap);
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't create and map bean: " + clazz);
        }
    }

    private void checkAvailability(Map parameterMap) {
        if (!parameterMap.containsKey(name)) {
            throw new IllegalArgumentException("Can't find value for primitive: " + name);
        }
    }

    private Object createForPrimitive(Map parameterMap) {
        checkAvailability(parameterMap);

        String strValue = ((String[]) parameterMap.get(name))[0];
        if (clazz == int.class || clazz == Integer.class) {
            return Integer.valueOf(strValue);
        } else if (clazz == String.class) {
            return strValue;
        }
        throw new IllegalArgumentException("Can't convert to primitive type: " + clazz);
    }

    private static boolean isSimpleType(Class<?> clazz) {
        return clazz == int.class || clazz == Integer.class || clazz == String.class;
    }

    private Object createAndMapForComplexType(Map parameterMap) throws Exception{
        Object bean = Class.forName(clazz.getCanonicalName()).newInstance();

        if (parameterMap == null || parameterMap.isEmpty()) {
            return bean;
        }

        for (Object key : parameterMap.keySet()) {
            String keyStr = (String) key;

            String field = keyStr.substring(keyStr.indexOf('.') + 1, keyStr.length());

            String value = ((String[]) parameterMap.get(key))[0];
            mapBeanSetter(bean, field, value);
        }
        return bean;
    }

    private void mapBeanSetter(Object bean, String field, String value) {
        try {
            invokeSetter(bean, field, value);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void invokeSetter(Object bean, String field, String value) throws InvocationTargetException, IllegalAccessException {
        if (field.indexOf(".") == -1) {
            getSetterMethod(bean, field).invoke(bean, value);
        } else {
            String newField = field.split("\\.")[0];
            Method setter = getSetterMethod(bean, newField);
            Method getter = getGetterMethod(bean, newField);

            Object subBean = getter.invoke(bean);
            if (subBean == null) {
                Class<?> returnClass = getter.getReturnType();
                try {
                    subBean = returnClass.newInstance();
                } catch (InstantiationException e) {
                    throw new IllegalStateException("Can't create bean: " + returnClass);
                }
                setter.invoke(bean, subBean);
            }

            invokeSetter(subBean, field.substring(field.indexOf('.') + 1, field.length()), value);
        }
    }

    private Method getGetterMethod(Object bean, final String field) {
        return FluentIterable.from(Arrays.asList(bean.getClass().getDeclaredMethods()))
                .firstMatch(new Predicate<Method>() {
                    @Override
                    public boolean apply(Method method) {
                        return method.getName().equals(genGetterName(field));
                    }
                }).orNull();
    }

    private static String genGetterName(String field) {
        return "get" + caseFieldName(field);
    }

    private static String caseFieldName(String field) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, field);
    }

    private Method getSetterMethod(Object bean, final String field) {
        return FluentIterable.from(Arrays.asList(bean.getClass().getDeclaredMethods()))
                .firstMatch(new Predicate<Method>() {
                    @Override
                    public boolean apply(Method method) {
                        return method.getName().equals(genSetterName(field));
                    }
                }).orNull();
    }

    private String genSetterName(String field) {
        return "set" + caseFieldName(field);
    }

    public static void main(String[] args) {
        Class<?> clazz = int.class;

        System.out.println(clazz.isPrimitive());
    }
}
