package com._candoit.drfood.global.validator;

import com._candoit.drfood.global.enums.ReturnCode;
import com._candoit.drfood.global.exception.DrFoodRequestException;

public class PageLimitSizeValidator {
    public static void validateSize(int page, int limit, int maxLimitSize) {
        if (page < 0 || limit <= 0 || limit > maxLimitSize) {
            throw new DrFoodRequestException(ReturnCode.WRONG_PARAMETER);
        }
    }
}
