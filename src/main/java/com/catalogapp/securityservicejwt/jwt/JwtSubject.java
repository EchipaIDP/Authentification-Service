package com.catalogapp.securityservicejwt.jwt;

import com.catalogapp.securityservicejwt.domain.Role;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class JwtSubject {

    private String username;

    private Role role;

    @Override
    public String toString() {
        var json = new Gson();
        return json.toJson(this);
    }
}
