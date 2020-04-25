package com.joy.spring.bean;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/25 15:16
 * mysql 继承的UserDao
 */
public class UserMysqlDaoImpi implements UserDao {
    @Override
    public void show() {
        System.out.println("mysqldao执行");
    }
}
