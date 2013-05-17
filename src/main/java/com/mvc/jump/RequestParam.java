package com.mvc.jump;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({ PARAMETER, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    String value();
}
