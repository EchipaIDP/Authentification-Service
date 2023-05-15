package com.catalogapp.securityservicejwt.jwt;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class JwtSubject {

    private String username;

    private String role;

    @Override
    public String toString() {
        var json = new Gson();
        return json.toJson(this);
    }
}
