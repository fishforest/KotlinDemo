package com.fish.kotlindemo.datastore

import androidx.datastore.core.Serializer
import com.fish.kotlindemo.test.UserPreferences
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer:Serializer<UserPreferences> {

    override val defaultValue: UserPreferences
        get() = UserPreferences.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): UserPreferences {
        return UserPreferences.parseFrom(input)
    }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        t.writeTo(output)
    }
}