package com.example.demo.constant;

public class RegularExpressionConstants {
    public static final String DEFAULT_EMAIL_REGEXP = "[a-z0-9._+-%]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$";
    public static final String DEFAULT_PASSWORD_REGEXP = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$";
    public static final String DEFAULT_REGEXP_TOTP = "[0-9]+";

}
