package com.sully.sensitivehandler;

import java.lang.annotation.*;

/**
 * Created by lei.s on 2017/8/17.
 * 敏感信息注解标记
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SensitiveInfo {

    public SensitiveInfoUtils.SensitiveType type() ;
}
