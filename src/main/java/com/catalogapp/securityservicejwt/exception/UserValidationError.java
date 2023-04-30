package com.catalogapp.securityservicejwt.exception;

import lombok.Getter;

@Getter
public enum UserValidationError {
    EMPTY_EMAIL(400,"Email can't be empty"),
    EMPTY_USERNAME(400,"Username can't be empty"),
    EMPTY_PASSWORD(400,"Password can't be empty"),
    EMPTY_FIRST_NAME(400,"First Name can't be empty"),
    EMPTY_LAST_NAME(400,"Last Name can't be empty"),
    WRONG_FORMAT_EMAIL(400,"Wrong Email format"),
    WRONG_FORMAT_PASSWORD(400,"Wrong Password Format!"),
    EXISTING_USERNAME(400,"Error: Username is already taken!"),
    EXISTING_EMAIL(400,"Error: Email is already taken!");

    UserValidationError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;
}
