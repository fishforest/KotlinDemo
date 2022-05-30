package com.fish.kotlindemo.classobject;

//基类
public class JavaParent {
    public String name;
    public void setName(String name) {
        this.name = name;
    }
}

class JavaSon extends JavaParent {
    //重写属性
    public String name;

    //重写方法
    @Override
    public void setName(String name) {
        super.setName(name);
        System.out.println("name:" + this.name);
    }
}

interface JavaInter {
    void printInter();
}

class JavaClass implements JavaInter {
    @Override
    public void printInter() {
    }
}
