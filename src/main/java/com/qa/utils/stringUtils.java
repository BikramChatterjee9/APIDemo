package com.qa.utils;

public class stringUtils {

    public static String getRandomEmail()
    {
        String email="test"+System.currentTimeMillis()+"@xyz.com";
        return email;
    }
}
