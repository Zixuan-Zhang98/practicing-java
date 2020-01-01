package com.zixuan.reflect.test;

import org.junit.Test;

public class ClassTest {

    @Test
    /**
     * 获得Class对象
     * 1. 通过 类名.class
     * 2. 对象.getClass()
     * 3. Class.forName()
     */
    public void demo1() throws ClassNotFoundException {
        // 1. 类名.class
        Class class1 = Person.class;

        // 2. 对象.getClass()
        Person person = new Person();
        Class class2 = person.getClass();

        // 3. Class.forName();
        Class.forName("com.zixuan.reflect.test.Person");
    }

}
