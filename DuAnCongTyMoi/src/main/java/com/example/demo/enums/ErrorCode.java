package com.example.demo.enums;

import com.example.demo.config.LabelKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    //Email
    EMS1001(LabelKey.ERROR_EMAIL_INVALID),
    EMS1002(LabelKey.ERROR_EMAIL_NULL),
    PAWS1003(LabelKey.ERROR_PASSWORD_INVALID),
    PAWS1004(LabelKey.ERROR_PASSWORD_NULL),
    ;
    private String key;


}
