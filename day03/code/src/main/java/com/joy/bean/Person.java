package com.joy.bean;

import java.util.Arrays;
import java.util.List;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/28 1:32
 */
public class Person {
    private int id;
    private String name;
    private int age;
    //数字的运用
    private String[] hobbies;
    //list的操作
    private List<String> list;
    //增加复杂的操作
    private List<Address> addList;

    public List<Address> getAddList() {
        return addList;
    }

    public void setAddList(List<Address> addList) {
        this.addList = addList;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

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

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", list=" + list +
                ", addList=" + addList +
                '}';
    }
}

