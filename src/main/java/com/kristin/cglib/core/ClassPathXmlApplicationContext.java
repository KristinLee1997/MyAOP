package com.kristin.cglib.core;


import com.kristin.cglib.aop.ProxyFactoryBean;
import com.kristin.cglib.config.Bean;
import com.kristin.cglib.config.Property;
import com.kristin.cglib.config.parsing.ConfigurationManager;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 11:21
 * @desc
 **/
public class ClassPathXmlApplicationContext implements BeanFactory {
    //配置信息
    private Map<String, Bean> config;

    //存放bean对象的容器
    private Map<String, Object> context = new HashMap<>();

    /**
     * 根据配置文件,将单例的bean对象放到context,而prototype的bean对象等到使用时再实例化
     *
     * @param path
     */
    public ClassPathXmlApplicationContext(String path) {
        config = ConfigurationManager.getBeanConfig(path);
        if (config != null) {
            for (Map.Entry<String, Bean> e : config.entrySet()) {
                String beanName = e.getKey();
                Bean bean = e.getValue();
                if (bean.getScope().equals(Bean.SINGLETON)) {
                    Object beanObj = createBeanByConfig(bean);
                    context.put(beanName, beanObj);
                }
            }
        }

    }

    /**
     * 根据bean的配置实例化bean
     *
     * @param bean
     * @return
     */
    private Object createBeanByConfig(Bean bean) {
        // 根据bean信息创建对象
        Class clazz = null;
        Object beanObj = null;
        try {
            clazz = Class.forName(bean.getClassName());
            // 创建bean对象
            beanObj = clazz.newInstance();
            // 获取bean对象中的property配置
            List<Property> properties = bean.getProperties();
            // 遍历bean对象中的property配置,并将对应的value或者ref注入到bean对象中
            for (Property prop : properties) {
                Map<String, Object> params = new HashMap<>();
                if (prop.getValue() != null) {
                    params.put(prop.getName(), prop.getValue());
                    // 将value值注入到bean对象中
                    BeanUtils.populate(beanObj, params);
                } else if (prop.getRef() != null) {
                    Object ref = context.get(prop.getRef());
                    // 如果依赖对象还未被加载则递归创建依赖的对象
                    if (ref == null) {
                        ref = createBeanByConfig(config.get(prop.getRef()));
                    }
                    params.put(prop.getName(), ref);
                    // 将ref对象注入bean对象中
                    BeanUtils.populate(beanObj, params);
                }
            }

            // 说明是要创建代理对象
            if (clazz.equals(ProxyFactoryBean.class)) {
                ProxyFactoryBean factoryBean = (ProxyFactoryBean) beanObj;
                // 创建代理对象
                beanObj = factoryBean.createProxy();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
            throw new RuntimeException("创建" + bean.getClassName() + "对象失败");
        }
        return beanObj;
    }

    @Override
    public Object getBean(String name) {
        Bean bean = config.get(name);
        Object beanObj = null;
        if (bean.getScope().equals(Bean.SINGLETON)) {
            beanObj = context.get(name);
        } else if (bean.getScope().equals(Bean.PROTOTYPE)) {
            beanObj = createBeanByConfig(bean);
        }
        return beanObj;
    }
}
