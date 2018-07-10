package com.kristin.jdk.dao;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 14:27
 * @desc
 **/
public class UserDaoImpl implements UserDao {
    @Override
    public void add(String user) {
        System.out.println("Add " + user);
    }

    @Override
    public String getUser(String id) {
        System.out.println("getUser " + id);
        return id + ":Jason";
    }
}
