package com.example.kson.ksonrxjava.entity;

import retrofit2.http.PUT;

public class BaseResponseEntity<T> {
    public String message;
    public String status;

    public T result;
}
