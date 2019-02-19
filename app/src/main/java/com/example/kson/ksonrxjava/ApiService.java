package com.example.kson.ksonrxjava;

import com.example.kson.ksonrxjava.entity.BaseResponseEntity;
import com.example.kson.ksonrxjava.entity.ProductEntity;
import com.example.kson.ksonrxjava.entity.UserEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Observable<BaseResponseEntity<List<ProductEntity>>> getProducts(@Url String url, @QueryMap HashMap<String,String> key);
}
