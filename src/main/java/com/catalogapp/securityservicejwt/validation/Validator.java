package com.catalogapp.securityservicejwt.validation;

import com.catalogapp.securityservicejwt.controller.request.SignupRequest;

public interface Validator {
    void validate(SignupRequest userDto);
}
