package com._candoit.drfood.param;

import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.enums.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MenuRecommendationParam {
    private Long score;
    private Menu menu;
    private RiskLevel riskLevel;
}
