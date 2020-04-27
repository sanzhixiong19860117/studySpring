package com.joy.bean.com.joy.factory;

import com.joy.bean.Person;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/28 1:44
 */
public class ObjFactory {
    public Person getPersonObj() {
        Person person = new Person();
        person.setAge(12);
        return person;
    }
}
