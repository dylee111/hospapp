package com.dydy.hospapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiReturn<T> {

    private Long size;
    private T data;

    public ApiReturn(Long size, T data) {
        this.size = size;
        this.data = data;
    }
}
