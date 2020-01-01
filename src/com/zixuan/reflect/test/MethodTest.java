package com.zixuan.reflect.test;

import org.junit.Test;

import java.lang.reflect.Method;

public class MethodTest {

    @Test
    /**
     * 测试公有方法
     */
    public void demo1() throws Exception {
        // 获得class
        Class class1 = Class.forName("com.zixuan.reflect.test.Person");
        // 实例化
        Person person = (Person) class1.getConstructor().newInstance();
        // 获得方法
        Method method = class1.getMethod("eat");
        // 执行方法
        method.invoke(person);
    }

    @Test
    /**
     * 测试私有方法
     */
    public void demo2() throws Exception{
        // 获得class
        Class class1 = Class.forName("com.zixuan.reflect.test.Person");
        // 实例化
        Person person = (Person) class1.getConstructor().newInstance();
        // 获得方法
        Method method = class1.getDeclaredMethod("run");
        // 私有方法需要设置一个可访问的权限
        method.setAccessible(true);
        // 执行方法
        method.invoke(person); // 相当于person.eat()
    }

    @Test
    /**
     * 测试私有，带参数的方法
     */
    public void demo3() throws Exception{
        // 获得class
        Class class1 = Class.forName("com.zixuan.reflect.test.Person");
        // 实例化
        Person person = (Person) class1.getConstructor().newInstance();
        // 获得方法
        Method method = class1.getMethod("eat");
        // 执行方法
        method.invoke(person);
    }

}
