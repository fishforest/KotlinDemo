package com.fish.kotlindemo.funclass;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public class TestJava {
    private void test() {
//        ClassFunKt.testFun2(new Function2<Integer, String, String>() {
//            @Override
//            public String invoke(Integer integer, String s) {
//                return null;
//            }
//        });
//
////        ClassFunKt.getTest1().invoke()
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

    private void testKotlin() {
        UpFunKt.testUpFun3(true, new Function2<String, Integer, Float>() {
            @Override
            public Float invoke(String s, Integer integer) {
                return null;
            }
        });
    }

    private void test1() {
        List nameList = new ArrayList();
        //添加字符串
        nameList.add("fish");
        //添加数字
        nameList.add(3);

        String ss = (String)nameList.get(0);
        int age = (int)nameList.get(1);
    }

    private void test2() {
        List<String> nameList = new ArrayList();
        //添加字符串
        nameList.add("fish");
        //添加数字
        nameList.add("forest");
        //编译器不允许
//        nameList.add(3);

        //无需强转
        String name1 = nameList.get(0);
        String name2 = nameList.get(1);
    }

    private void testExpand() {
        //需要传入扩展类的对象实例
        boolean b1 = ExpandFunKt.isFirstUpper("Fish");
        boolean b2 = ExpandFunKt.isFirstUpper("1fish");
    }
}

