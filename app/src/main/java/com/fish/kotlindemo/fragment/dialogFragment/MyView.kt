package com.fish.kotlindemo.fragment.dialogFragment

import android.content.Context
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class MyView(context:Context):AppCompatTextView(context) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}