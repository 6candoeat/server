package com._candoit.drfood.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RiskCountParam {

    private int safeCount;
    private int moderateCount;
    private int highRiskCount;
}
