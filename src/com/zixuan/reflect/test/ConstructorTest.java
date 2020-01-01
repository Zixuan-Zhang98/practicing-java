package com.zixuan.reflect.test;

import org.junit.Test;

import java.lang.reflect.Constructor;

public class ConstructorTest {

    @Test
    /**
     * 获得无参数的构造方法
     */
    public void demo1() throws Exception {
        // 1. 获得类的字节码文件对以后的对象
        Class class1 = Class.forName("com.zixuan.reflect.test.Person");
        Constructor c = class1.getConstructor();
        Person person = (Person) c.newInstance(); //相当于Person person = new Person();
        person.eat();
    }

    @Test
    /**
     * 获得有参数的构造方法
     */
    public void demo2() throws Exception {
        // 1. 获得类的字节码文件对以后的对象
        Class class1 = Class.forName("com.zixuan.reflect.test.Person");
        Constructor c = class1.getConstructor(String.class, String.class);
        Person person = (Person) c.newInstance("Din", "male"); //相当于Person person = new Person("Din", "male");
        System.out.println(person);
    }


}
