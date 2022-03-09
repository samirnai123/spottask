package com.spot.task.services.generic;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;


public class MyResponseEntity<T> extends ResponseEntity<T> {

    private MyResponseEntity(T body, HttpStatus status) {
        super(body, status);
    }

    private MyResponseEntity(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    private MyResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    private MyResponseEntity(HttpStatus status) {
        super(status);
    }

    public static <T> MyResponseEntity<GenResponse<T>> OK(String title, String message, T data) {
        return new MyResponseEntity<GenResponse<T>>(GenResponse.OK(title, message, data), HttpStatus.OK);
    }

    public static MyResponseEntity<GenResponse<Void>> OK(String title, String message) {
        return new MyResponseEntity<>(GenResponse.OK(title, message), HttpStatus.OK);
    }

    public static MyResponseEntity<GenResponse<Void>> OKWithNagetive(String title, String message) {
        return new MyResponseEntity<GenResponse<Void>>(
            GenResponse.OKWithNagetive(title, message), HttpStatus.OK);
    }

    public static <T> MyResponseEntity<GenResponse<T>> OK(String title,
                                                          String message, @Nullable HttpHeaders headers, T data) {
        return new MyResponseEntity<GenResponse<T>>(
            GenResponse.OK(title, message, data), headers, HttpStatus.OK);
    }

    public static MyResponseEntity<GenResponse<Void>> OK(String title, String message, HttpHeaders headers) {
        return new MyResponseEntity<GenResponse<Void>>(GenResponse.OK(title, message), headers, HttpStatus.OK);
    }
}
