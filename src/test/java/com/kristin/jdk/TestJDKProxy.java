package com.kristin.jdk;

import com.kristin.jdk.core.BeanFactory;
import com.kristin.jdk.core.ClassPathXmlApplicationContext;
import com.kristin.jdk.dao.UserDao;
import org.junit.Test;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 14:56
 * @desc
 **/
public class TestJDKProxy {
    @Test
    public void testJDKProxy() {
        BeanFactory ac = new ClassPathXmlApplicationContext("/applicationContext-jdk.xml");
        UserDao userDao = (UserDao) ac.getBean("userDaoProxy");
        System.out.println(userDao.getClass());
        userDao.add("kristin");
        String user = userDao.getUser("123");
        System.out.println(user);
    }
}
