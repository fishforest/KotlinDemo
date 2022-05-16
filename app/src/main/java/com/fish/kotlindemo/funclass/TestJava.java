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

//        ClassFunKt.getTest1().invoke()
    }


    private void testStudent(HandleStudent handleStudent) {
        float score = 0;
        if (handleStudent != null) {
            score = handleStudent.getScore("fish", 18);
        }
        System.out.println("score:" + score);
    }


    interface HandleStudent {
        //传入学生的姓名、年龄，返回学生的分数
        float getScore(String name, int age);
    }
}
