package com.fish.kotlindemo.coroutinestory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class JavaStudent {

    Callable<StudentInfo> callable = new Callable<StudentInfo>() {
        @Override
        public StudentInfo call() throws Exception {
            try {
                //模拟耗时操作
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new StudentInfo();
        }
    };

    public StudentInfo getStuInfo(long stuId) {
        //定义任务
        FutureTask<StudentInfo> futureTask = new FutureTask<>(callable);
        //开启线程，执行任务
        new Thread(futureTask).start();
        try {
            //阻塞获取结果
            StudentInfo studentInfo = futureTask.get();
            return studentInfo;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //从后台获取信息
    public StudentInfo getWithoutThread(long stuId) {
        try {
            //模拟耗时操作
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new StudentInfo();
    }

    //回调接口
    public interface Callback {
        void onCallback(StudentInfo studentInfo);
        //新增老师回调接口
        default void onCallback(TeacherInfo teacherInfo){}
    }

    //异步调用
    public void getStuInfoAsync(long stuId, Callback callback) {
        new Thread(() -> {
            try {
                //模拟耗时操作
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            StudentInfo studentInfo = new StudentInfo();
            if (callback != null) {
                callback.onCallback(studentInfo);
            }
        }).start();
    }

    //异步调用
    public void getTeachInfoAsync(long stuId, Callback callback) {
        //先获取学生信息
        getStuInfoAsync(stuId, new Callback() {
            @Override
            public void onCallback(StudentInfo studentInfo) {
                //获取学生信息后，取出关联的语文老师id，获取老师信息
                new Thread(() -> {
                    try {
                        //模拟耗时操作
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    TeacherInfo teacherInfo = new TeacherInfo();
                    if (callback != null) {
                        //老师信息获取成功
                        callback.onCallback(teacherInfo);
                    }
                }).start();
            }
        });
    }
}
