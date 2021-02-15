package com.mercadolibre.ipinfo.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    boolean response;
    boolean exception;
    String message;
    Object object;

    public void init(){
        this.response = true;
        this.message = "OK";
        this.object = null;
    }

    public void failed(String message, boolean exception){
        this.response = false;
        this.exception = exception;
        this.message = message;
        this.object = null;
    }

    public void success(String message, Object object){
        this.response = true;
        this.message = message;
        this.object = object;
    }
}
