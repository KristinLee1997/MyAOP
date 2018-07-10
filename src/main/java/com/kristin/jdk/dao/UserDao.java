package com.kristin.jdk.dao;

/**
 * @author 李航
 * @school 哈尔滨理工大学
 * @date 2018/7/10 14:26
 * @desc
 **/
public interface UserDao {
    public void add(String user);

    public String getUser(String id);
}
