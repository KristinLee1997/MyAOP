package com.kristin.demo;

import com.kristin.demo.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 14:23
 * @desc
 **/
public class TestProxy {
    @Test
    public void test() {
        ApplicationContext atx = new ClassPathXmlApplicationContext("classpath:applicationContext_demo.xml");
        UserDao userDao = (UserDao) atx.getBean("userDaoProxy");
        userDao.add("kristin");
        System.out.println(userDao.getClass());
    }
}
