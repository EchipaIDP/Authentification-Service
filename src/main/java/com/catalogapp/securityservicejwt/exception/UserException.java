package com.catalogapp.securityservicejwt.exception;


import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private Integer code;

    private String message;

    public UserException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
