package com.joy.spring.demo;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/25 15:36
 */

/**
 * 人物类的创建
 */
public class Person {
    private int id;
    private String name;
    private int age;
    private int sex;

    public Person(){};

    public Person(int id, String name, int age, int sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
