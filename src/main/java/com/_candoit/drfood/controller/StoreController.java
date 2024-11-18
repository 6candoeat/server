package com._candoit.drfood.controller;

import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.domain.Store;
import com._candoit.drfood.enums.Category;
import com._candoit.drfood.global.response.ApiResponse;
import com._candoit.drfood.global.response.DrFoodPage;
import com._candoit.drfood.global.validator.PageLimitSizeValidator;
import com._candoit.drfood.param.RiskCountParam;
import com._candoit.drfood.service.DiseaseMenuRiskService;
import com._candoit.drfood.service.MenuService;
import com._candoit.drfood.service.StoreService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    private final MenuService menuService;

    private final DiseaseMenuRiskService diseaseMenuRiskService;


    @GetMapping("/{memberId}")
    public ApiResponse getStores(@RequestParam("category") Category category, @PathVariable("memberId") Long memberId, StoreGetRequest request) {
        PageLimitSizeValidator.validateSize(request.getPage(), request.getLimit(), 100);
        Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());

        List<StoreItem> storeList = new ArrayList<>();

        //카테고리 맞는 가게 페이징
        Page<Store> stores = storeService.getStores(category, pageable);

        //가게별 메뉴를 가져와서 메뉴마다 위험도 개수를 구해 return
        for (Store store : stores) {
            Page<Menu> menus = menuService.findMenusByStore(store, pageable);
            RiskCountParam count = diseaseMenuRiskService.getRiskLevelCount(memberId, menus);
            storeList.add(StoreItem.of(store, count));
        }
        Page<StoreItem> storeItems = new PageImpl<>(storeList, pageable, storeList.size());

        return ApiResponse.of(DrFoodPage.of(storeItems));
    }

    @Data
    private static class StoreGetRequest {

        private int page = 0;
        private int limit = 10;
    }

    @Data
    private static class StoreItem {

        private String storeName;

        private String storePictureUrl;

        private int safe;

        private int moderate;

        private int highRisk;

        private static StoreItem of(Store store, RiskCountParam riskCountParam) {
            StoreItem converted = new StoreItem();
            converted.storeName = store.getStoreName();
            converted.storePictureUrl = store.getStorePictureUrl();
            converted.safe = riskCountParam.getSafeCount();
            converted.moderate = riskCountParam.getModerateCount();
            converted.highRisk = riskCountParam.getHighRiskCount();
            return converted;
        }
    }
}
