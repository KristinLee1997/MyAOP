package com.kristin.cglib.config.parsing;


import com.kristin.cglib.config.Bean;
import com.kristin.cglib.config.Property;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 10:45
 * @desc
 **/
public class ConfigurationManager {
    public static Map<String, Bean> getBeanConfig(String path){
        //存放配置信息,返回结果
        Map<String, Bean> result = new HashMap<>();
        //通过dom4j,创建解析器
        SAXReader reader = new SAXReader();
        //获取配置文件流
        InputStream is = ConfigurationManager.class.getResourceAsStream(path);
        //使用解析器解析流
        Document doc = null;
        try {
            doc = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        String xpath = "//bean";
        //获取Document中的元素
        List<Element> beanNodes = doc.selectNodes(xpath);
        //遍历所有bean结点,将信息封装在Bean对象中
        for (Element ele : beanNodes) {
            Bean bean = new Bean();
            bean.setName(ele.attributeValue("name"));
            bean.setClassName(ele.attributeValue("class"));
            String scope = ele.attributeValue("scope");
            //如果指定了scope,而且scope不是空串的话,就设置scope,否则就默认scope是SINGLETON
            if (scope != null && scope.trim().length() > 0) {
                bean.setScope(scope);
            }
            List<Element> propNodes = ele.elements("property");
            if (propNodes != null) {
                for (Element prop : propNodes) {
                    Property property = new Property();
                    property.setName(prop.attributeValue("name"));
                    property.setValue(prop.attributeValue("value"));
                    property.setRef(prop.attributeValue("ref"));
                    bean.getProperties().add(property);
                }
            }
            result.put(bean.getName(), bean);
        }
        return result;
    }
}
