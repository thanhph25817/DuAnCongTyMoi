package com.example.demo;

import com.example.demo.constant.RegularExpressionConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Validator {
    // kiem tra xem email co dung dinh dang khong
    public static boolean ValidateEmail(String email) {
        return !Pattern.compile(RegularExpressionConstants.DEFAULT_EMAIL_REGEXP).
                matches(RegularExpressionConstants.DEFAULT_EMAIL_REGEXP, email);
    }

    public static boolean ValdidatePassword(String password) {
        return !Pattern.compile(RegularExpressionConstants.DEFAULT_PASSWORD_REGEXP).
                matches(RegularExpressionConstants.DEFAULT_PASSWORD_REGEXP, password);
    }

    public static boolean isNull(String s) {
        return StringUtils.isBlank(s) || StringUtils.isEmpty(s);
    }
}
