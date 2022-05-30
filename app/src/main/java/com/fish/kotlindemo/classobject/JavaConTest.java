package com.fish.kotlindemo.classobject;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public class JavaConTest {
    private String name;
    private int age;
    private float score;

    //构造函数1
    public JavaConTest(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //构造函数2
    public JavaConTest(String name, int age, float score) {
        this(name, age);
        this.score = score;
    }
}