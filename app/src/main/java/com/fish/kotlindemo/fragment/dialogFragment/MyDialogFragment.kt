package com.fish.kotlindemo.fragment.dialogFragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelStore
import com.fish.kotlindemo.vm.MyViewModel2

class MyDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tv = TextView(context).apply {
            text = "hello world"
            setTextColor(Color.RED)
            textSize = 30f
        }
        return tv
    }
}