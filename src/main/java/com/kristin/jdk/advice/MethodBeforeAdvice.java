package com.kristin.jdk.advice;

import java.lang.reflect.Method;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 14:29
 * @desc
 **/
public interface MethodBeforeAdvice {
    void before(Method method, Object[] args, Object target);
}
