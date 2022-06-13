package com.fish.kotlindemo.varclass;

import android.os.Bundle;
import android.widget.Toast;

import com.fish.kotlindemo.coroutinestory.JavaStudent;
import com.fish.kotlindemo.coroutinestory.StudentInfo;
import com.fish.kotlindemo.funclass.Student;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestJavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void click() {
        JavaStudent javaStudent = new JavaStudent();
        StudentInfo studentInfo = javaStudent.getWithoutThread(999);
        Toast.makeText(this, "学生姓名:" + studentInfo.getName(), Toast.LENGTH_LONG).show();
    }

    private void onClick2() {
        JavaStudent javaStudent = new JavaStudent();
        javaStudent.getStuInfoAsync(999, new JavaStudent.Callback() {
            @Override
            public void onCallback(StudentInfo studentInfo) {
                //异步调用，回调从子线程返回，需要切换到主线程更新UI
                runOnUiThread(() -> {
                    Toast.makeText(TestJavaActivity.this, "学生姓名:" + studentInfo.getName(), Toast.LENGTH_LONG).show();
                });
            }
        });
    }
}
