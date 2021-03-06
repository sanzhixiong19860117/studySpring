package com.test;

import com.joy.bean.Address;
import com.joy.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/26 15:39
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
        //注意点
        //通过id
//        final Person person = context.getBean("person", Person.class);
        //通过类型来查找
        final Person person2 = context.getBean("person", Person.class);
        System.out.println(person2);
    }
}
