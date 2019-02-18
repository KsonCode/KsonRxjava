package com.example.kson.ksonrxjava;

import com.example.kson.ksonrxjava.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET
    Observable<UserEntity> getUserInfo(@Query("uid") String url);
}
