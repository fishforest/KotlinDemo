package com.fish.kotlindemo.coroutine;

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
        lable:
        {
            int a = 5;
            tt = new TestJava();
        }
        System.out.println("before");
        tt.test();
        System.out.println("after");
    }

}
