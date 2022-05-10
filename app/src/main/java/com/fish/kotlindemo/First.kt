package com.fish.kotlindemo

open class First {
   var name:String = ""
    get() {
        return ""
    }

    set(value) {
        field = if (value == null)
            "3"
        else
            "4"
    }
}