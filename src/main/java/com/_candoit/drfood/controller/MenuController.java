package com._candoit.drfood.controller;

import com._candoit.drfood.domain.Nutrition;
import com._candoit.drfood.domain.Risk;
import com._candoit.drfood.enums.RiskLevel;
import com._candoit.drfood.global.response.ApiResponse;
import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.global.response.DrFoodPage;
import com._candoit.drfood.global.validator.PageLimitSizeValidator;
import com._candoit.drfood.param.MenuRecommendationParam;
import com._candoit.drfood.service.DiseaseMenuRiskService;
import com._candoit.drfood.service.MenuService;
import com._candoit.drfood.service.RecommendationService;
import com._candoit.drfood.service.RiskService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    private final RecommendationService recommendationService;

    private final DiseaseMenuRiskService diseaseMenuRiskService;

    private final RiskService riskService;

    @GetMapping("/menus/{menuId}/{memberId}")
    public ApiResponse getMenu(@PathVariable("menuId") Long menuId, @PathVariable("memberId") Long memberId) {
        Menu menu = menuService.getMenu(menuId);
        Nutrition nutrition = menuService.getNutrition(menu);
        RiskLevel riskLevel = diseaseMenuRiskService.getRiskLevel(memberId, menu);
        return ApiResponse.of(MenuItem.of(menu, riskLevel, nutrition));
    }

    @GetMapping("/stores/{storeId}/{memberId}")
    public ApiResponse getMenus(@PathVariable("storeId") Long storeId, @PathVariable("memberId") Long memberId, MenuGetRequest request) {
        PageLimitSizeValidator.validateSize(request.getPage(), request.getLimit(), 100);
        Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());

        List<MenuItem> menuItemList = new ArrayList<>();

        List<Risk> allByMemberIdAndStoreId = riskService.findAllByMemberIdAndStoreId(memberId, storeId);
        //가게 메뉴를 가져와서 메뉴마다 위험도 구해 return
        Page<Menu> menus = menuService.findMenusByStoreId(storeId, pageable);
        for (Risk risk : allByMemberIdAndStoreId) {
            menuItemList.add(MenuItem.of(risk.getMenu(), risk.getRiskLevel()));
        }

        Page<MenuItem> menuItems = new PageImpl<>(menuItemList, pageable, menuItemList.size());
        return ApiResponse.of(DrFoodPage.of(menuItems));
    }

    @GetMapping("/recommendation/{memberId}")
    public ApiResponse getRecommendStores(@PathVariable("memberId") Long memberId, MenuGetRequest request) {
        PageLimitSizeValidator.validateSize(request.getPage(), request.getLimit(), 100);
        Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());

        List<MenuRecommendationParam> recommendMenuList = new ArrayList<>();

        //모든 메뉴 가져오기
        List<Risk> menus = riskService.findAllExecuteHighRisk(memberId);

        //메뉴별 위험도 계산해서 안전, 보통만 추천
        for (Risk risk : menus) {
            recommendMenuList.add(MenuRecommendationParam.builder().menu(risk.getMenu()).riskLevel(risk.getRiskLevel()).build());
        }

        List<MenuItem> collect = recommendationService.recommendMenu(memberId, recommendMenuList).stream().map(param -> MenuItem.of(param.getMenu(), param.getRiskLevel()))
                .collect(Collectors.toList());

        Page<MenuItem> menuItems = new PageImpl<>(collect, pageable, collect.size());

        return ApiResponse.of(DrFoodPage.of(menuItems));
    }

    @Data
    private static class MenuGetRequest {

        private int page = 0;
        private int limit = 30;
    }

    @Data
    private static class MenuItem {
        private Long menuId;

        private String menuName;

        private String description;

        private int price;

        private String menuImageUrl;

        private RiskLevel riskLevel;

        private BigDecimal calories;

        private BigDecimal fat;

        private BigDecimal saturatedFat;

        private BigDecimal transFat;

        private BigDecimal sodium;

        private BigDecimal carbohydrates;

        private BigDecimal protein;

        private BigDecimal dietaryFiber;

        private BigDecimal sugar;

        private static MenuItem of(Menu menu, RiskLevel riskLevel) {
            MenuItem converted = new MenuItem();
            converted.setMenuId(menu.getMenuId());
            converted.setMenuName(menu.getMenuName());
            converted.setDescription(menu.getDescription());
            converted.setPrice(menu.getPrice());
            converted.setMenuImageUrl(menu.getMenuImageUrl());
            converted.setRiskLevel(riskLevel);
            return converted;
        }

        private static MenuItem of(Menu menu, RiskLevel riskLevel, Nutrition nutrition) {
            MenuItem converted = new MenuItem();
            converted.setMenuId(menu.getMenuId());
            converted.setMenuName(menu.getMenuName());
            converted.setDescription(menu.getDescription());
            converted.setPrice(menu.getPrice());
            converted.setMenuImageUrl(menu.getMenuImageUrl());
            converted.setRiskLevel(riskLevel);
            converted.setCalories(nutrition.getCalories());
            converted.setFat(nutrition.getFat());
            converted.setSaturatedFat(nutrition.getSaturatedFat());
            converted.setTransFat(nutrition.getTransFat());
            converted.setSodium(nutrition.getSodium());
            converted.setCarbohydrates(nutrition.getCarbohydrates());
            converted.setProtein(nutrition.getProtein());
            converted.setDietaryFiber(nutrition.getDietaryFiber());
            converted.setSugar(nutrition.getSugar());
            return converted;
        }
    }
}
