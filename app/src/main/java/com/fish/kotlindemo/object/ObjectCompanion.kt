package com.fish.kotlindemo.`object`

class ObjectCompanion {
    fun test() {
        for (i in 1..100) {
            KotlinStatic.buildBean()
        }
    }
}

class KotlinStatic {
    private val name: String? = null
    private val age = 0
    private val score = 0f

    companion object StudentFactory : EasyJavaInterface {
        //伴生对象函数
        fun buildBean(): KotlinStatic {
            //不允许访问外部变量
//            score = 13.f
            return KotlinStatic()
        }

        override fun getStuName(): String {
            TODO("Not yet implemented")
        }
    }
}

class KotlinStatic1 {
    private val name: String? = null
    private val age = 0
    private val score = 0f

    companion object {
        //伴生对象函数
        fun buildBean(): KotlinStatic {
            return KotlinStatic()
        }
    }
}