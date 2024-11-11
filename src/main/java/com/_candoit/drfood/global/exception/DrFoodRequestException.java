package com._candoit.drfood.global.exception;

import com._candoit.drfood.global.enums.ReturnCode;

public class DrFoodRequestException extends DrFoodException{
    public DrFoodRequestException(ReturnCode returnCode) {
        super(returnCode);
    }
}
