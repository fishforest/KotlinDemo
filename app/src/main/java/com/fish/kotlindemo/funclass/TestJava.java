package com.fish.kotlindemo.funclass;

import kotlin.jvm.functions.Function2;

public class TestJava {
    private void test() {
        ClassFunKt.testFun2(new Function2<Integer, String, String>() {
            @Override
            public String invoke(Integer integer, String s) {
                return null;
            }
        });
    }
}
