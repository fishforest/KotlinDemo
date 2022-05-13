package com.fish.kotlindemo.varclass;

import android.util.Log;

import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;

class TestJava {
    void test() {
        int a = 5;

        //小转大  允许
        long b = a;

        //大转小 允许
        a = (int) b;
    }

    void test2() {
        //调用方法
        VarTestKt.test2();
//        //获取变量
//        VarTestKt.getNum();
//        //设置变量
//        VarTestKt.setNum(33);

        //直接访问属性
        VarTestKt.num = 2.4;
        double num = VarTestKt.num;
    }

    void test3() {
        VarTestClass varTestClass = new VarTestClass();
//        varTestClass.num = 3
        varTestClass.getNum();
        varTestClass.setNum(3);
    }

    void test4() {

    }

    void test(String... names) {
        for (String name : names) {
            Log.d("fish", name);
        }
    }
}
