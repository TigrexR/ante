package com.tigrex.api.annotation;

import com.tigrex.api.constant.ContextConst;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface DataSource {

    ContextConst.DataSourceType value() default ContextConst.DataSourceType.USER;

}
