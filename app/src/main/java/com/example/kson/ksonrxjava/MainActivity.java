package com.example.kson.ksonrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kson.ksonrxjava.entity.BaseResponseEntity;
import com.example.kson.ksonrxjava.entity.ProductEntity;
import com.example.kson.ksonrxjava.entity.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//数据解析器，gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//数据请求适配器,支持rxjava的
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        HashMap<String,String> params = new HashMap<>();
        params.put("keyword","电脑");
        params.put("page","1");
        params.put("count","10");
        apiService.getProducts(Api.PRODUCT_URL,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResponseEntity<List<ProductEntity>>>() {
            @Override
            public void accept(BaseResponseEntity<List<ProductEntity>> productEntityBaseResponseEntity) throws Exception {

                List<ProductEntity> list = productEntityBaseResponseEntity.result;
                System.out.println("size:"+list.size());

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

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


        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {


            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            // 采用flatMap（）变换操作符
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("s", s);
            }
        });


    }
}
