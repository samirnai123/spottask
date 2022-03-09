package com.spot.task.services.generic;

import javax.validation.constraints.NotNull;


public class GenericDTO<T> {

    @NotNull(message = "{validation.notNull.data}")
    private T data;

    public GenericDTO(T data) {
        this.data = data;
    }

    public GenericDTO() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
