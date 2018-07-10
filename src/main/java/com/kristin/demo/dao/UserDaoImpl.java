package com.kristin.demo.dao;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 14:16
 * @desc
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public void add(String user) {
        System.out.println("add " + user);
    }
}
