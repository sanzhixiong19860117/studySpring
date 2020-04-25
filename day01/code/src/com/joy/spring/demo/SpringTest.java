package com.joy.spring.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/25 15:48
 */
public class SpringTest {
    public static void main(String[] args) {
        //使用容器的操作
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
        //使用对象操作
        final Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person.getId());
    }
}
