package com.ningning0111.aop;

import java.lang.annotation.*;

/**
 * @author ：何汉叁
 * @date ：2024/4/10 19:37
 * @description：TODO
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用在参数和方法上
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Documented//表明这个注解应该被 javadoc工具记录
public @interface SystemControllerLog {
    String description() default "";
}