package com._candoit.drfood.service;

import com._candoit.drfood.domain.Member;
import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.domain.Nutrition;
import com._candoit.drfood.domain.Risk;
import com._candoit.drfood.enums.RiskLevel;
import com._candoit.drfood.enums.UserDisease;
import com._candoit.drfood.global.enums.ReturnCode;
import com._candoit.drfood.global.exception.DrFoodLogicException;
import com._candoit.drfood.repository.MemberRepository;
import com._candoit.drfood.repository.MenuRepository;
import com._candoit.drfood.repository.RiskRepository;
import com._candoit.drfood.validator.RiskLevelValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RiskService {

    private final DiseaseMenuRiskService diseaseMenuRiskService;

    private final MenuRepository menuRepository;

    private final RiskRepository riskRepository;

    private final MemberRepository memberRepository;


    public void create(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
        List<Menu> menus = menuRepository.findAll();
        for (Menu menu : menus) {
            RiskLevel riskLevel = diseaseMenuRiskService.getRiskLevel(memberId, menu);
            Risk risk = Risk.builder().riskLevel(riskLevel).member(member).menu(menu).build();
            riskRepository.save(risk);
        }

    }
}
