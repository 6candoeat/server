package com._candoit.drfood.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCode {
    SUCCESS("0000", "Success"),

    WRONG_PARAMETER("4000", "Wrong parameter"),
    NOT_FOUND_ENTITY("4001", "Not found entity"),
    NOT_AUTHORIZED("4004", "Not authorized"),

    INTERNAL_ERROR("5000", "Unexpected internal error");

    private String returnCode;
    private String returnMessage;
}
