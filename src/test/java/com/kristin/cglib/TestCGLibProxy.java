package com.kristin.cglib;


import com.kristin.cglib.core.BeanFactory;
import com.kristin.cglib.core.ClassPathXmlApplicationContext;
import com.kristin.cglib.dao.UserDao;
import org.junit.Test;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 18:57
 * @desc
 **/
public class TestCGLibProxy {
    @Test
    public void testCGLibProxy() {
        BeanFactory ac = new ClassPathXmlApplicationContext("/applicationContext-cglib.xml");
        UserDao userDao = (UserDao) ac.getBean("userDaoProxy");
        System.out.println(userDao.getClass());
        userDao.add("kristin");
        String user = userDao.getUser("123");
        System.out.println(user);
    }
}
