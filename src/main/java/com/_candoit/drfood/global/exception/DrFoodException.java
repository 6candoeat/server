package com._candoit.drfood.global.exception;

import com._candoit.drfood.global.enums.ReturnCode;
import lombok.Getter;

@Getter
public class DrFoodException extends RuntimeException {

    private ReturnCode returnCode;
    private String returnMessage;

    public DrFoodException(ReturnCode returnCode) {
        this.returnCode = returnCode;
        this.returnMessage = returnCode.getReturnMessage();
    }
}
