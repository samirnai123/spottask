package com.spot.task.resources.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

import java.util.HashMap;
import java.util.Map;

import static org.zalando.problem.Status.BAD_REQUEST;
import static org.zalando.problem.Status.OK;

public class CustomException extends AbstractThrowableProblem {

    private static final String KEY_SUCCESS = "success";
    private static final String KEY_CODE = "code";
    private static final String KEY_MESSAGE = "message";

    private CustomException(String message, Status code) {
        super(ErrorConstants.PARAMETERIZED_TYPE, message, BAD_REQUEST, null, null, null, getParamMap(message, code));
    }

    private CustomException(String title, String message, Status code) {
        super(ErrorConstants.PARAMETERIZED_TYPE, title, OK, null, null, null, getParamMap(message, code));
    }

    public CustomException(String title, String message, StatusType apiCode, Status code) {
        super(ErrorConstants.PARAMETERIZED_TYPE, title, apiCode, null, null, null, getParamMap(message, code));
    }


    public static CustomException throwMe(String message, Status code) {
        return new CustomException(message, code);
    }

    public static CustomException throwMe(String title, String message, Status code) {
        return new CustomException(title, message, code);
    }

    private static Map<String, Object> getParamMap(String message, Status code) {
        Map<String, Object> map = new HashMap<>(3);
        map.put(KEY_SUCCESS, false);
        map.put(KEY_MESSAGE, message);
        map.put(KEY_CODE, code);
        return map;
    }


}

