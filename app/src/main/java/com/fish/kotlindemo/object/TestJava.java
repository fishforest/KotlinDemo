package com.fish.kotlindemo.object;

public class TestJava {
    //继承接口
    class MyInter implements JavaInterface {
        @Override
        public String getStuName() {
            return null;
        }

        @Override
        public int getStuAge() {
            return 0;
        }
    }
    public static void main(String args[]) {
        TestJava testJava = new TestJava();
//        //实例化接口
//        MyInter myInter = new TestJava().new MyInter();
//        //传入参数
//        testJava.getStuInfo(myInter);

        //匿名内部类
        testJava.getStuInfo(new JavaInterface() {
            @Override
            public String getStuName() {
                return "fish";
            }
            @Override
            public int getStuAge() {
                return 18;
            }
        });

        testJava.getStuInfo(new JavaAbClass() {
            @Override
            public int getStuAge() {
                return 18;
            }
        });
    }

    public void getStuInfo(JavaInterface javaInterface) {
        String name = javaInterface.getStuName();
        int age = javaInterface.getStuAge();
    }

    public void getStuInfo(JavaAbClass javaAbClass) {
        String name = javaAbClass.getStuName();
        int age = javaAbClass.getStuAge();
    }
}
