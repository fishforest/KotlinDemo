package com.fish.kotlindemo.fragment.dialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fish.kotlindemo.vm.MyViewModel2

class FishPureFragment:Fragment() {

    private val vm by viewModels<MyViewModel2>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val textView:TextView = TextView(requireContext())
        textView.text = "hello world"
        textView.textSize = 30f
        vm.testLife()
        return textView
    }
}