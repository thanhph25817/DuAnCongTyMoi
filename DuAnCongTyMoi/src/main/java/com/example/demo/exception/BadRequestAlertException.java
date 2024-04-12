package com.example.demo.exception;

import com.example.demo.config.Labels;
import com.example.demo.constant.ApiConstants;
import com.example.demo.enums.ErrorCode;
import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

public class BadRequestAlertException extends AbstractThrowableProblem {
    private static final long serialVersionId = -6642851821916766469L;




    public BadRequestAlertException(ErrorCode errorCode) {
        this(Labels.getLabels(errorCode.getKey()), errorCode.name(), errorCode.getKey());
    }

    public BadRequestAlertException(String defaultMessage, String errorCode, String errorKey) {

    }
}
