package com._candoit.drfood.controller;

import com._candoit.drfood.enums.RiskLevel;
import com._candoit.drfood.global.response.ApiResponse;
import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.global.response.DrFoodPage;
import com._candoit.drfood.global.validator.PageLimitSizeValidator;
import com._candoit.drfood.repository.MemberRepository;
import com._candoit.drfood.service.DiseaseMenuRiskService;
import com._candoit.drfood.service.MenuService;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    private final MemberRepository memberRepository;

    private final DiseaseMenuRiskService diseaseMenuRiskService;

    @GetMapping("/menus/{menuId}/{memberId}")
    public ApiResponse getMenu(@PathVariable("menuId") Long menuId, @PathVariable("memberId") Long memberId) {
        Menu menu = menuService.getMenu(menuId);
        RiskLevel riskLevel = diseaseMenuRiskService.getRiskLevel(memberId, menu);
        return ApiResponse.of(MenuItem.of(menu, riskLevel));
    }

    @GetMapping("/stores/{storeId}/{memberId}")
    public ApiResponse getMenus(@PathVariable("storeId") Long storeId, @PathVariable("memberId") Long memberId, MenuGetRequest request) {
        PageLimitSizeValidator.validateSize(request.getPage(), request.getLimit(), 100);
        Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());

        List<MenuItem> menuItemList = new ArrayList<>();

        //가게 메뉴를 가져와서 메뉴마다 위험도 구해 return
        Page<Menu> menus = menuService.findMenusByStoreId(storeId, pageable);
        for (Menu menu : menus.getContent()) {
            RiskLevel riskLevels = diseaseMenuRiskService.getRiskLevel(memberId, menu);
            menuItemList.add(MenuItem.of(menu, riskLevels));
        }

        Page<MenuItem> menuItems = new PageImpl<>(menuItemList, pageable, menuItemList.size());
        return ApiResponse.of(DrFoodPage.of(menuItems));
    }

    @Data
    private static class MenuGetRequest {

        private int page = 0;
        private int limit = 10;
    }

    @Data
    private static class MenuItem {

        private String menuName;

        private String description;

        private int price;

        private String menuImageUrl;

        private RiskLevel riskLevel;

        private static MenuItem of(Menu menu, RiskLevel riskLevel) {
            MenuItem converted = new MenuItem();
            converted.setMenuName(menu.getMenuName());
            converted.setDescription(menu.getDescription());
            converted.setPrice(menu.getPrice());
            converted.setMenuImageUrl(menu.getMenuImageUrl());
            converted.setRiskLevel(riskLevel);
            return converted;
        }
    }
}
