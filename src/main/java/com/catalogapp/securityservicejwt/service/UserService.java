package com.catalogapp.securityservicejwt.service;


import com.catalogapp.securityservicejwt.controller.request.SignupRequest;
import com.catalogapp.securityservicejwt.domain.User;

public interface UserService {

    User saveUser(SignupRequest userDto);

    String getAuthenticatedUserId();

}
