package com.kristin.cglib.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 10:40
 * @desc
 **/
public class Bean {
    //如果没设置,默认scope就是单例模式
    public static final String SINGLETON = "singleton";
    public static final String PROTOTYPE = "prototype";

    private String name;
    private String className;
    private String scope = SINGLETON;
    private List<Property> properties = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", scope='" + scope + '\'' +
                ", properties=" + properties +
                '}';
    }
}
