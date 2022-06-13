package com.fish.kotlindemo.coroutinestory

import android.app.Activity
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentCoroutine {
    private val FIXED_TEACHER_ID = 888
    fun getTeachInfo(act: Activity, stuId: Long) {
        GlobalScope.launch(Dispatchers.Main) {

            var studentInfo: StudentInfo
            var teacherInfo: TeacherInfo? = null

            //先获取学生信息
            withContext(Dispatchers.IO) {
                //模拟网络获取
                Thread.sleep(2000)
                studentInfo = StudentInfo()
            }
            //再获取教师信息
            withContext(Dispatchers.IO) {
                if (studentInfo.lanTechId.toInt() === FIXED_TEACHER_ID) {
                    //模拟网络获取
                    Thread.sleep(2000)
                    teacherInfo = TeacherInfo()
                }
            }
            //更新UI
            Toast.makeText(act, "teacher name:${teacherInfo?.name}", Toast.LENGTH_LONG).show()
        }

        Toast.makeText(act, "主线程还在跑...", Toast.LENGTH_LONG).show()
    }
}

