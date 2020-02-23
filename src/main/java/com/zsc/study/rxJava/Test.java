package com.zsc.study.rxJava;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func0;

/**
 * @Auther: zhangshanchuang
 * @Date: 20/2/20 21:09
 * @Description:
 */
public class Test {

    static class a {
        public String call() {
            System.out.println("1");
            return "a";
        }
    }

    public static void main(String[] args) throws Exception {
        test();
    }

    static final Func0<Observable<String>> applyHystrixSemantics = new Func0<Observable<String>>() {
        @Override
        public Observable<String> call() {
            System.out.println("2222");
            return applyHystrixSemantics();
        }
    };

    private static Observable<String> applyHystrixSemantics() {
        System.out.println("3333");
        return Observable.from(new String[]{"1111"});
    }

    private static Observable<String> oncomplete(){
       return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Observable observable =Observable.defer(applyHystrixSemantics);
                System.out.println("4444");
                return observable;
            }
        }).doOnCompleted(new Action0() {
           @Override
           public void call() {
               System.out.println("5555");
           }
       });

    }

    private static void test()throws Exception {

       System.out.println(oncomplete().toBlocking().toFuture().get());
    }
}

