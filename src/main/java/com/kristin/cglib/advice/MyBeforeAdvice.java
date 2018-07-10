package com.kristin.cglib.advice;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 17:22
 * @desc
 **/
public class MyBeforeAdvice extends MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("前置通知");
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //前置通知方法,在目标方法前执行
        before(method, args, obj);
        return proxy == null ? method.invoke(obj, args) : proxy.invokeSuper(obj, args);
    }
}
