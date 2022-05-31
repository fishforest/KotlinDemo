package com.fish.kotlindemo.object;

public class JavaStatic {
    private String name;
    private int age;
    private float score;
    //构造Bean对象
    public static JavaStatic buildBean() {
        JavaStatic bean = new JavaStatic();
        return bean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
