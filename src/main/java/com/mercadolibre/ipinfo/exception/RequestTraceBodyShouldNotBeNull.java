package com.mercadolibre.ipinfo.exception;

public class RequestTraceBodyShouldNotBeNull extends Exception {

    public RequestTraceBodyShouldNotBeNull(){}

    public RequestTraceBodyShouldNotBeNull(String message){
        super(message);
    }

    public RequestTraceBodyShouldNotBeNull(String message, Throwable cause){
        super(message,cause);
    }

}
