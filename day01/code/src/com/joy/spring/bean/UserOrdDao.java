package com.joy.spring.bean;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/25 15:16
 * 奥约克继承UserDao操作
 */
public class UserOrdDao implements UserDao {
    @Override
    public void show() {
        System.out.println("奥约克数据库被调用");
    }
}
