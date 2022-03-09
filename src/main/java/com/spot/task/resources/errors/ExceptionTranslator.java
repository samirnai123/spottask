package com.spot.task.resources.errors;

import com.spot.task.resources.utils.MessageUtil;
import com.spot.task.services.generic.GenResponse;
import com.spot.task.services.generic.MyResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(value = CustomException.class)
    public MyResponseEntity<GenResponse<Void>> exception(CustomException customException){
        return MyResponseEntity.OK(customException.getMessage(),MessageUtil.ERROR);
    }
}
