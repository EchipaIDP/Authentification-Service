package com.catalogapp.securityservicejwt.utils;


import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Utils {

    public boolean isValidPassword(String password)
    {
        var regex ="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
