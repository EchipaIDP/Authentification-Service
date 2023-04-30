package com.catalogapp.securityservicejwt.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class SignupRequest {
    private final String username;

    private final String email;

    private final String password;

    private final String firstName;

    private final String lastName;
}
