package com.catalogapp.securityservicejwt.controller.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtResponse {
    private final String token;
    private final String type = "Bearer";
    private final String id;
    private final String username;
    private final String email;
    private final List<String> roles;
}
