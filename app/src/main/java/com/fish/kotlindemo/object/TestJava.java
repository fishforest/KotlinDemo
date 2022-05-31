package com.fish.kotlindemo.object;

import android.view.View;

import org.jetbrains.annotations.NotNull;

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

        testJava.getStuInfo(() -> "fish");

        //学生身高
        int height = 0;
        JavaInterface javaInterface = new JavaInterface() {
            @Override
            public String getStuName() {
                //编译错误
//                height = 180;
                return "fish";
            }

            @Override
            public int getStuAge() {
                return 18;
            }
        };

        JavaInterface javaInterface1 = new JavaInterface() {
            //新增分数
            private float score;
            public float getScore() {
                return score;
            }
            @Override
            public String getStuName() {
                return null;
            }

            @Override
            public int getStuAge() {
                return 0;
            }
        };
        //无法访问
//        javaInterface1.getScore();
    }

    public void getStuInfo(JavaInterface javaInterface) {
        String name = javaInterface.getStuName();
        int age = javaInterface.getStuAge();
    }

    public void getStuInfo(JavaAbClass javaAbClass) {
        String name = javaAbClass.getStuName();
        int age = javaAbClass.getStuAge();
    }

    public void getStuInfo(EasyJavaInterface easyJavaInterface) {
        String name = easyJavaInterface.getStuName();
    }

    public void testKtSingleton() {
        String name = KtSingleton.getStuName();
        int age = KtSingleton.age;
    }
}
