package com.kristin.jdk.advice;

import java.lang.reflect.Method;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 14:31
 * @desc
 **/
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("前置通知");
    }
}
