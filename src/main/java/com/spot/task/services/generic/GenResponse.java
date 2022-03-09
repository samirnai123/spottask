package com.spot.task.services.generic;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;


public class GenResponse<T> implements Serializable {

    private Boolean success;
    private Integer status;
    private String title;
    private String message;
    @ReadOnlyProperty
    private Instant timestamp = Instant.now();
    private T data;

    private GenResponse(Boolean success, Integer status, String title, String message) {
        this.success = success;
        this.status = status;
        this.title = title;
        this.message = message;
    }

    private GenResponse(Boolean success, Integer status, String title, String message, T data) {
        this.success = success;
        this.status = status;
        this.title = title;
        this.data = data;
        this.message = message;
    }

    public static <T> GenResponse<T> OK(String title, String message, T data) {
        return new GenResponse<T>(true, HttpStatus.OK.value(), title, message, data);
    }

    public static <T> GenResponse<T> OKWithNagetive(String title, String message, T data) {
        return new GenResponse<T>(false, HttpStatus.OK.value(), title, message, data);
    }

    public static <T> GenResponse<T> OK(String title, String message) {
        return new GenResponse<T>(true, HttpStatus.OK.value(), title, message);
    }

    public static <T> GenResponse<T> OKWithNagetive(String title, String message) {
        return new GenResponse<T>(false, HttpStatus.OK.value(), title, message);
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
