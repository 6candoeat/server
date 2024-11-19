package com._candoit.drfood.service;

import com._candoit.drfood.domain.Member;
import com._candoit.drfood.enums.RiskLevel;
import com._candoit.drfood.global.enums.ReturnCode;
import com._candoit.drfood.global.exception.DrFoodLogicException;
import com._candoit.drfood.param.MenuRecommendationParam;
import com._candoit.drfood.repository.MemberRepository;
import com._candoit.drfood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendationService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    public List<MenuRecommendationParam> recommendMenu(Long memberId, List<MenuRecommendationParam> recommendMenuList) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));

        List<MenuRecommendationParam> recommendScoreList = new ArrayList<>();
        long totalCount = orderRepository.count();
        long memberTotalCount = orderRepository.countByMember(member);
        long sameDiseaseTotalCount = orderRepository.countTotalOrdersByDisease(member.getUserDisease(), LocalDateTime.now().minusMonths(6));

        for (MenuRecommendationParam param : recommendMenuList) {
            long menuCount = orderRepository.countByMenu(param.getMenu());
            long memberMenuCount = orderRepository.countOrdersByMemberAndMenu(member, param.getMenu());
            long sameDiseaseMenuCount = orderRepository.countOrdersByDiseaseAndMenuInLastSixMonths(member.getUserDisease(), param.getMenu(), LocalDateTime.now().minusMonths(6));
            long safeScore;

            if (param.getRiskLevel().equals(RiskLevel.SAFE)) {
                safeScore = 40;
            } else {
                safeScore = 28;
            }

            long finalScore = safeScore + (menuCount / totalCount) * 10 + (memberMenuCount / memberTotalCount) * 20 + (sameDiseaseMenuCount / sameDiseaseTotalCount) * 30;

            recommendScoreList.add(MenuRecommendationParam.builder().menu(param.getMenu()).riskLevel(param.getRiskLevel()).score(finalScore).build());
        }

        recommendScoreList.sort(Comparator.comparing(MenuRecommendationParam::getScore).reversed());
        return recommendScoreList.subList(0, Math.min(10, recommendScoreList.size()));

    }
}
