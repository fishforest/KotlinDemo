package com.fish.kotlindemo

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fish.kotlindemo.coroutinestory.JavaStudent
import com.fish.kotlindemo.coroutinestory.StudentCoroutine
import com.fish.kotlindemo.coroutinestory.StudentInfo
import com.fish.kotlindemo.coroutinestory.TeacherInfo
import com.fish.kotlindemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.btnJava.setOnClickListener {
            var javaStudent = JavaStudent()
//            var studentInfo = javaStudent.getWithoutThread(999)
//            Toast.makeText(this, "学生姓名：${studentInfo?.name ?:"空"}", Toast.LENGTH_LONG).show()
            javaStudent.getStuInfoAsync(999) {
                runOnUiThread {
                    Toast.makeText(this, "学生姓名：${it?.name ?: "空"}", Toast.LENGTH_LONG).show()
                }
            }

            javaStudent.getTeachInfoAsync(999, object : JavaStudent.Callback {
                override fun onCallback(teacherInfo: TeacherInfo?) {
                    runOnUiThread {
                        Toast.makeText(binding.root.context,
                            "老师姓名：${teacherInfo?.name ?: "空"}",
                            Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCallback(studentInfo: StudentInfo?) {
                    TODO("Not yet implemented")
                }
            })
        }

        binding.btnKotlin.setOnClickListener {
            var student = StudentCoroutine()
            student.getTeachInfo(this@MainActivity, 999)
        }
    }
}