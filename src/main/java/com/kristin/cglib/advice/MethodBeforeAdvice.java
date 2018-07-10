package com.kristin.cglib.advice;

import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 17:18
 * @desc
 **/
public abstract class MethodBeforeAdvice implements MethodInterceptor {

    public abstract void before(Method method, Object[] args, Object target);

}
