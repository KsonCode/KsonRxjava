package com.example.kson.ksonrxjava;

import com.example.kson.ksonrxjava.entity.UserEntity;

import javax.xml.transform.Source;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Test {
    public static void main(String[] args){
        //1.创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            /**
             *
             * @param emitter 发射器，发射事件（事件就是数据）
             * @throws Exception
             */
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {//发射
                //发射器作用：发射事件
                emitter.onNext(1);//发射第一个事件
                emitter.onNext(2);//发射第二个事件
                emitter.onComplete();//发送事件结束的回调，complete（）和error只能执行一个
//                emitter.onError(new Throwable());//异常的时候激发的回调

            }
        });//创建操作符

        //2.创建观察者
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
//                System.out.println(d.isDisposed());

            }

            /**
             * 正确接收事件的方法
             * @param o
             */
            @Override
            public void onNext(Integer o) {

                System.out.println("receive:"+o);


            }

            @Override
            public void onError(Throwable e) {
                System.out.println("thrwoable："+e);
            }

            @Override
            public void onComplete() {


            }
        };


        //3.订阅
        observable.subscribe(observer);//观察者订阅被观察者

        //链式调用
        Observable.create(new ObservableOnSubscribe<UserEntity>() {

            @Override
            public void subscribe(ObservableEmitter<UserEntity> emitter) throws Exception {
//                emitter.onNext("kson");
//                emitter.onNext("kson1");
//                emitter.onNext("kson2");
                System.out.println("observable:"+Thread.currentThread().getName());
                emitter.onNext(new UserEntity("h"));
                emitter.onNext(new UserEntity("h1"));
                emitter.onNext(new UserEntity("h2"));
                emitter.onNext(new UserEntity("h3"));
                emitter.onNext(new UserEntity("h4"));
            }
        }).subscribe(new Observer<UserEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserEntity s) {
                System.out.println("observer:"+Thread.currentThread().getName());
                System.out.println(s.name);//


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
