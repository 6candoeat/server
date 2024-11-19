package com._candoit.drfood.service;

import com._candoit.drfood.domain.Member;
import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.domain.Risk;
import com._candoit.drfood.domain.Store;
import com._candoit.drfood.enums.Category;
import com._candoit.drfood.enums.RiskLevel;
import com._candoit.drfood.global.enums.ReturnCode;
import com._candoit.drfood.global.exception.DrFoodLogicException;
import com._candoit.drfood.repository.MemberRepository;
import com._candoit.drfood.repository.MenuRepository;
import com._candoit.drfood.repository.RiskRepository;
import com._candoit.drfood.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiskService {

    private final DiseaseMenuRiskService diseaseMenuRiskService;

    private final MenuRepository menuRepository;

    private final RiskRepository riskRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    public void create(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
        List<Menu> menus = menuRepository.findAll();
        for (Menu menu : menus) {
            RiskLevel riskLevel = diseaseMenuRiskService.getRiskLevel(memberId, menu);
            Risk risk = Risk.builder().riskLevel(riskLevel).member(member).menu(menu).build();
            riskRepository.save(risk);
        }
    }

    public List<Risk> findAllByMemberIdAndStoreId(Long memberId, Long storeId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
        return riskRepository.findAllByMemberAndStore(member, store);
    }

    public List<Risk> findAllByMemberIdAndCategory(Long memberId, Category category) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
        return riskRepository.findAllByMemberAndCategory(member, category);
    }

    public List<Risk> findAllExecuteHighRisk(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
        return riskRepository.findMenusByMemberAndExcludeHighRisk(member);
    }
}
