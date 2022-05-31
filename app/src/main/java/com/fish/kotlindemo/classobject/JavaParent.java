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

class Outer {
    private String name;
    //成员方法
    private void printName() {
        System.out.println("name:" + name);
    }

    private static String staticName;
    static void printStaticName() {
    }

    //非静态内部类
    class Inner {
        public void testInner() {
            //访问外部类方法
            printName();
            //访问外部类属性
            name = "fish";
        }
    }

    //静态内部类
    static class StaticInner {
        public void testInner() {
            //访问静态方法
            printStaticName();
            //访问静态属性
            staticName = "fish";
        }
    }
}
