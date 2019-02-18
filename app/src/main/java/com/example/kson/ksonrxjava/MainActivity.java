package com.example.kson.ksonrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kson.ksonrxjava.entity.UserEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxjava();
//        Thread thread = new Thread("xianchengg");
//        thread.start();
        retrofitRxjava();
    }

    private void retrofitRxjava() {
        Retrofit retrofit = new Retrofit.Builder().build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getUserInfo("71")
                .subscribeOn(Schedulers.io())//指定被观察者线程，请求网络线程
                .observeOn(AndroidSchedulers.mainThread())//指定观察者线程，接收数据线程
                .subscribe(new Observer<UserEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //是否解除订阅,返回false则未解除,返回true则代表解除
                        System.out.println(d.isDisposed());


                    }

                    @Override
                    public void onNext(UserEntity userEntity) {//正确回调的数据

                    }

                    @Override
                    public void onError(Throwable e) {//异常回调

                    }

                    @Override
                    public void onComplete() {//事件完成回调

                    }
                });
    }

    private void rxjava() {
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
                emitter.onComplete();
            }
            //subcribeon指定被观察者执行线程，observeOn:指定观察者执行线程
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserEntity>() {
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
                System.out.println("e:");

            }

            @Override
            public void onComplete() {
                System.out.println("oncomplete");

            }
        });


    }
}
