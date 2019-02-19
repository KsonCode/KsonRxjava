package com.example.kson.ksonrxjava;

import android.util.Log;

import com.example.kson.ksonrxjava.entity.UserEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.Source;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Test {
    private final static String TAG = Test.class.getSimpleName();//获取类的名称
    static Disposable disposable = null;
    public static void main(String[] args){

//        //1.创建被观察者
//        Observable observable = Observable.create(new ObservableOnSubscribe() {
//            /**
//             *
//             * @param emitter 发射器，发射事件（事件就是数据）
//             * @throws Exception
//             */
//            @Override
//            public void subscribe(ObservableEmitter emitter) throws Exception {//发射
//                //发射器作用：发射事件
//                emitter.onNext(1);//发射第一个事件
//                emitter.onNext(2);//发射第二个事件
//                emitter.onComplete();//发送事件结束的回调，complete（）和error只能执行一个
////                emitter.onError(new Throwable());//异常的时候激发的回调
//
//            }
//        });//创建操作符
//
//        //2.创建观察者
//        Observer<Integer> observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
////                System.out.println(d.isDisposed());
//
//            }
//
//            /**
//             * 正确接收事件的方法
//             * @param o
//             */
//            @Override
//            public void onNext(Integer o) {
//
//                System.out.println("receive:"+o);
//
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("thrwoable："+e);
//            }
//
//            @Override
//            public void onComplete() {
//
//
//            }
//        };
//
//
//        //3.订阅
//        observable.subscribe(observer);//观察者订阅被观察者
//
//        //链式调用
//        Observable.create(new ObservableOnSubscribe<UserEntity>() {
//
//            @Override
//            public void subscribe(ObservableEmitter<UserEntity> emitter) throws Exception {
////                emitter.onNext("kson");
////                emitter.onNext("kson1");
////                emitter.onNext("kson2");
//                System.out.println("observable:"+Thread.currentThread().getName());
//                emitter.onNext(new UserEntity("h"));
//                emitter.onNext(new UserEntity("h1"));
//                emitter.onNext(new UserEntity("h2"));
//                emitter.onNext(new UserEntity("h3"));
//                emitter.onNext(new UserEntity("h4"));
//            }
//        }).subscribe(new Observer<UserEntity>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(UserEntity s) {
//                System.out.println("observer:"+Thread.currentThread().getName());
//                System.out.println(s.name);//
//
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("1");
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
//
//            //
//            @Override
//            public void onSubscribe(Disposable d) {
//
//                disposable = d;
//                System.out.println(d.isDisposed());//是否解除订阅
//
//            }
//
//            @Override
//            public void onNext(String s) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//
//        disposable.dispose();//解除的方法


//        Observable.just("1","gdgedg","RERER").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                System.out.println(s);
//            }
//        });
//
//        // 1. 设置需要传入的数组
//        Integer[] items = { 0, 1, 2, 3, 4 };
//        Observable.fromArray(items).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                System.out.println(integer);
//            }
//        });
//
//        List<String> list = new ArrayList<>();
//        list.add("212121");
//        list.add("434343");
//        Observable.fromIterable(list).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//
//                System.out.println(s);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//
//            }
//        });
//
//        Observable.timer(2,TimeUnit.SECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//
//                System.out.println("long"+aLong);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Observable.interval(3,1,TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Long value) {
                        System.out.println(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }

                });


        Observable.just(99.99,100,3.10).map(new Function<Number, String>() {

            @Override
            public String apply(Number number) throws Exception {
                return "¥："+number;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

//被观察者在主线程中，每1ms发送一个事件
        Observable.interval(1, TimeUnit.MILLISECONDS)
                //.subscribeOn(Schedulers.newThread())
                //将观察者的工作放在新线程环境中
                .observeOn(Schedulers.newThread())
                //观察者处理每1000ms才处理一个事件
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.w("TAG","---->"+aLong);
                    }
                });

    }
}
