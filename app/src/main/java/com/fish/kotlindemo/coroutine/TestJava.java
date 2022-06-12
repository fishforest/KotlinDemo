package com.fish.kotlindemo.coroutine;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

class TestJava {
    public static void main(String args[]) {
        TestJava testJava = new TestJava();
        testJava.stt();
    }

    private void test() {
        CoroutineJJ coroutineJJ = new CoroutineJJ();
//        coroutineJJ.test2(new Continuation<Unit>() {
//            @NotNull
//            @Override
//            public CoroutineContext getContext() {
//                return null;
//            }
//
//            @Override
//            public void resumeWith(@NotNull Object o) {
//
//            }
//        });
    }

    private void stt() {
        TestJava tt;
        lable:{
        int a = 5;
        tt = new TestJava();
        }
        System.out.println("before");
        tt.test();
        System.out.println("after");
    }

}
