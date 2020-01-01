package com.zixuan.reflect.test;

import org.junit.Test;

import java.lang.reflect.Field;

public class FieldTest {

    @Test
    /**
     * 反射公有属性
     */
    public void demo1() throws Exception {
        // 获得Class
        Class class1 = Class.forName("com.zixuan.reflect.test.Person");

        // 获得属性
        Field nameField = class1.getField("name");
        // 操作属性 p.name = "Din"
        Person p = (Person) class1.getConstructor().newInstance();
        nameField.set(p, "Din");

        Object obj = nameField.get(p);
        System.out.println(obj);
    }

    @Test
    /**
     * 反射私有属性
     */
    public void demo2() throws Exception {
        // 获得Class
        Class class1 = Class.forName("com.zixuan.reflect.test.Person");

        // 获得属性
        Field sexField = class1.getDeclaredField("sex");
        // 操作属性 p.name = "Din"
        Person p = (Person) class1.getConstructor().newInstance();
        // 私有属性需要设置一个可访问的权限
        sexField.setAccessible(true);
        sexField.set(p, "male");

        System.out.println(p);
        // 获取值
        Object obj = sexField.get(p);
        System.out.println(obj);
    }

}
