package com.fish.kotlindemo.object;

public class JavaSingleton {
    private static volatile JavaSingleton instance;
    private JavaSingleton(){}

    //双重检测锁
    public JavaSingleton getInstance() {
        if (instance == null) {
            synchronized (JavaSingleton.class) {
                if (instance == null) {
                    instance = new JavaSingleton();
                }
            }
        }
        return instance;
    }
}
