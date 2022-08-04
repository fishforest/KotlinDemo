package com.fish.kotlindemo.cancelexception;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class DebugJava {

    class Human {

    }

    class Man extends Human{

    }

    private void testList() {
        List<? extends Human> humans = new ArrayList<Man>();
        List<? super Man> humanj = new ArrayList<Human>();
        humanj.add(new Man());
    }


    class MyHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(@NonNull @NotNull Thread t, @NonNull @NotNull Throwable e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


    private void testEx() {
//        Thread.setDefaultUncaughtExceptionHandler(new MyHandler());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("java");
                try {
                    throw new RuntimeException("fuck exception");
                } catch (Exception e) {

                }
                Thread thread = Thread.currentThread();
                Throwable throwable = new Throwable("just test");
                thread.getUncaughtExceptionHandler().uncaughtException(thread, throwable);
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("jj test");
                }
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("java main running...");
    }

    public static void main(String args[]) {
        DebugJava debugJava = new DebugJava();
        debugJava.testEx();
    }
}
