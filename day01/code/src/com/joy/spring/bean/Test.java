package com.joy.spring.bean;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/25 15:19
 */
public class Test {
    public static void main(String[] args) {
        UserMysqlDaoImpi userMysqlDaoImpi = new UserMysqlDaoImpi();
        UserOrdDao userOrdDao = new UserOrdDao();

        //创建测试类
        MyTest myTest = new MyTest();
        myTest.setUser(userMysqlDaoImpi);
        System.out.println("-----------------------");
        myTest.setUser(userOrdDao);
    }
}
