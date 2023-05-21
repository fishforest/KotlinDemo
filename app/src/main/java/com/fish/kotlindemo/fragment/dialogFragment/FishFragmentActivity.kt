package com.fish.kotlindemo.fragment.dialogFragment

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.fish.kotlindemo.R
import com.fish.kotlindemo.vm.MyViewModel2

var count = 1
class FishFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            it.getBundle("androidx.lifecycle.BundleSavedStateRegistry.key")?.remove("android:support:fragments");
        }
        setContentView(R.layout.layout_fragment )
        val fragment = FishPureFragment()
        fragment.arguments = Bundle().apply {
            putString("hello", "fragment:${count++}")
        }
//        supportFragmentManager.beginTransaction().replace(R.id.root, fragment).commitNow()

        MyDialogFragment().show(supportFragmentManager, "dd")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}