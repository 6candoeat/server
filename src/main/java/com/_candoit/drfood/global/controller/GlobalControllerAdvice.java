package com._candoit.drfood.global.controller;

import com._candoit.drfood.global.exception.DrFoodException;
import com._candoit.drfood.global.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DrFoodException.class)
    public ApiResponse<?> handleDrFoodException(DrFoodException e) {
        return ApiResponse.of(e.getReturnCode());
    }
}
