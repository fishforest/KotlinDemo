package com.fish.kotlindemo.classobject;

class Start {
    private void test() {
        //构造非静态内部类
        Outer.Inner inner = new Outer().new Inner();
        inner.testInner();

        //构造静态内部类
        Outer.StaticInner staticInner = new Outer.StaticInner();
        staticInner.testInner();
    }
}