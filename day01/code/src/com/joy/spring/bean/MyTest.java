package com.joy.spring.bean;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/25 15:23
 */
public class MyTest{

    private UserDao userDao;

    public MyTest(){

    }

    public MyTest(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 设置一个接口进行向上转换
     * @param userDao
     */
    public void setUser(UserDao userDao){
        this.userDao = userDao;
        userDao.show();
    }
}
