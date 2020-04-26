package com.joy.bean;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/26 15:36
 */
public class Person {
    private int id;
    private String name;
    private int age;

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("第二个构造函数");
    }

    public Person(int id, int age) {
        this.id = id;
        this.age = age;
        System.out.println("第一个构造函数");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
