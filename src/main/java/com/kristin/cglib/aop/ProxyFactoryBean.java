package com.kristin.cglib.aop;

import com.kristin.cglib.advice.MethodBeforeAdvice;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 17:27
 * @desc
 **/
public class ProxyFactoryBean {
    //目标对象
    private Object target;

    //通知
    private MethodBeforeAdvice interceptor;

    //代理实现的接口
    private String proxyInterface;

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setInterceptor(MethodBeforeAdvice interceptor) {
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
            return CGLibProxy();
        }
        return JDKProxy();
    }

    /**
     * 创建JDK代理对象
     *
     * @return
     */
    public Object JDKProxy() {
        Class<?> clazz;
        try {
            clazz = Class.forName(proxyInterface);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(proxyInterface + "找不到,请注意填写正确");
        }
        Object proxyInstance = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**
                 * 第四个参数如果为null代表 JDK代理
                 */
                return interceptor.intercept(target, method, args, null);
            }
        });
        return proxyInstance;
    }

    /**
     * 创建CGLib代理对象
     *
     * @return
     */
    public Object CGLibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(interceptor);
        return enhancer.create();
    }
}
