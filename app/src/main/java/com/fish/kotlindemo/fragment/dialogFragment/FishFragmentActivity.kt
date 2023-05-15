package com.fish.kotlindemo.fragment.dialogFragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.fish.kotlindemo.vm.MyViewModel2

class FishFragmentActivity : AppCompatActivity() {

    private val fragment = FishPureFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this)
        linearLayout.id = View.generateViewId()
        linearLayout.orientation = LinearLayout.VERTICAL
        val btn = Button(this)
        btn.text = "remove"
        btn.setOnClickListener {
            supportFragmentManager.beginTransaction().remove(fragment).commitNow()
        }
        linearLayout.addView(btn)
        setContentView(linearLayout)

        supportFragmentManager.beginTransaction().add(linearLayout.id, fragment).commitNow()
    }
}