package com.ashokit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private int code;

    private String status;

    private T data;

    public BaseResponse(){

        this.code = 200;

        this.status = "SUCCESS";

    }



}
