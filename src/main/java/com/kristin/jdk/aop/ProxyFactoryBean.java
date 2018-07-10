package com.kristin.jdk.aop;

import com.kristin.jdk.advice.MethodBeforeAdvice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 14:33
 * @desc
 **/
public class ProxyFactoryBean {
    //目标对象
    private Object target;

    //通知
    private Object interceptor;

    //代理实现的接口
    private String proxyInterface;

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setInterceptor(Object interceptor) {
        this.interceptor = interceptor;
    }

    public void setProxyInterface(String proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    /**
     * 创建代理对象
     *
     * @return
     */
    public Object createProxy() {
        if (proxyInterface == null || proxyInterface.trim().length() == 0) {
            return createCGLibProxy();
        }
        return createJDKProxy();
    }

    /**
     * 创建JDK代理对象
     *
     * @return
     */
    public Object createJDKProxy() {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(proxyInterface);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(proxyInterface + "找不到,请正确填写");
        }
        //JDK方式生成的代理对象
        Object proxyInstance = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;
                        //要先判断interceptor是哪种通知类型,以决定执行目标方法的位置
                        //判断interceptor是否为前置的通知
                        if (interceptor instanceof MethodBeforeAdvice) {
                            MethodBeforeAdvice advice = (MethodBeforeAdvice) interceptor;
                            advice.before(method, args, target);
                            result = method.invoke(target, args);
                        }
                        return result;
                    }
                });
        return proxyInstance;
    }

    /**
     * 创建cglib代理对象
     *
     * @return
     */
    public Object createCGLibProxy() {
        return null;
    }
}
