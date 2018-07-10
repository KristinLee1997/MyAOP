package com.kristin.demo.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 14:17
 * @desc
 **/
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("------------------前置通知-------------------");
        System.out.println("方法: " + method.getName());
        System.out.println("参数: " + args);
        System.out.println("目标对象: " + target);
        System.out.println("------------------前置通知-------------------");
    }
}
