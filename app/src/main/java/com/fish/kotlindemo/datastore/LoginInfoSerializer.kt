package com.fish.kotlindemo.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.PreferencesMapCompat
import androidx.datastore.preferences.PreferencesProto
import androidx.datastore.preferences.core.*
import com.fish.kotlindemo.test.LoginInfo
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlin.jvm.Throws

object LoginInfoSerializer : Serializer<LoginInfo> {
    //默认值
    override val defaultValue: LoginInfo
        get() = LoginInfo.getDefaultInstance()

    //如何从文件里读取Protobuf内容
    override suspend fun readFrom(input: InputStream): LoginInfo {
        return LoginInfo.parseFrom(input)
    }

    //将Protobuf写入到文件
    override suspend fun writeTo(t: LoginInfo, output: OutputStream) {
        t.writeTo(output)
    }
}
