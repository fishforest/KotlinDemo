package com.fish.kotlindemo.sequence;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import androidx.annotation.RequiresApi;

public class Java8Stream {

    private List<Integer> list = new ArrayList<>();

    public Java8Stream() {
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
    }

    public List<Integer> dealCollection() {
        List<Integer> evenList = new ArrayList<>();
        for (Integer integer : list) {
            //筛选出偶数
            if (integer % 2 == 0) {
                evenList.add(integer);
            }
        }

        List<Integer> bigList = new ArrayList<>();
        for (Integer integer : evenList) {
            //从偶数中筛选出大于1000的数
            if (integer > 1000) {
                bigList.add(integer);
            }
        }
        //返回筛选结果列表
        return bigList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public long dealCollectionWithStream() {
        Stream<Integer> stream = list.stream();
        return stream.filter(value -> value % 2 == 0)
                .filter(value -> value > 1000)
                .count();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String args[]) {

        SequenceDemo sequenceDemo = new SequenceDemo();
        //使用集合操作
        sequenceDemo.testCollection();
        //使用sequence操作
        sequenceDemo.testSequence();
        sequenceDemo.testSequence1();

        Java8Stream java8Stream = new Java8Stream();
        //普通集合耗时
        long startTime = System.currentTimeMillis();
        List<Integer> list = java8Stream.dealCollection();
        System.out.println("java7 list size:" + list.size() + " use time:" + (System.currentTimeMillis() - startTime) + "ms");

        //Stream API 的耗时
        long startTime2 = System.currentTimeMillis();
        long count = java8Stream.dealCollectionWithStream();
        System.out.println("java8 stream list size:" + count + " use time:" + (System.currentTimeMillis() - startTime2) + "ms");
    }
}
