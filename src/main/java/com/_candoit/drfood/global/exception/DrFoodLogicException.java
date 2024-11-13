package com._candoit.drfood.global.exception;

import com._candoit.drfood.global.enums.ReturnCode;

public class DrFoodLogicException extends DrFoodException{
    public DrFoodLogicException(ReturnCode returnCode) {
        super(returnCode);
    }
}
