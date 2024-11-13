package com._candoit.drfood.menu.controller;

import com._candoit.drfood.global.response.ApiResponse;
import com._candoit.drfood.menu.domain.Ingredient;
import com._candoit.drfood.menu.domain.Menu;
import com._candoit.drfood.menu.domain.Nutrition;
import com._candoit.drfood.menu.service.MenuService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{menuId}")
    public ApiResponse getMenu(@PathVariable("menuId") Long menuId) {
        Menu menu = menuService.getMenu(menuId);
        return ApiResponse.of(MenuItem.of(menu));
    }

    @GetMapping("/nutrition/{menuId}")
    public ApiResponse getNutrition(@PathVariable("menuId") Long menuId) {
        Nutrition nutrition = menuService.getNutrition(menuId);
        return ApiResponse.of(NutritionItem.of(nutrition));
    }

    @GetMapping("/ingredient/{menuId}")
    public ApiResponse getIngredient(@PathVariable("menuId") Long menuId) {
        Ingredient ingredient = menuService.getIngredient(menuId);
        return ApiResponse.of(IngredientItem.of(ingredient));
    }

    @Data
    private static class MenuItem {

        private String menuName;

        private String description;

        private int price;

        private String menuImageUrl;

        private static MenuItem of(Menu menu) {
            MenuItem converted = new MenuItem();
            converted.setMenuName(menu.getMenuName());
            converted.setDescription(menu.getDescription());
            converted.setPrice(menu.getPrice());
            converted.setMenuImageUrl(menu.getMenuImageUrl());
            return converted;
        }
    }

    @Data
    private static class NutritionItem {

        private BigDecimal calories;

        private BigDecimal fat;

        private BigDecimal saturatedFat;

        private BigDecimal transFat;

        private BigDecimal sodium;

        private BigDecimal carbohydrates;

        private BigDecimal protein;

        private BigDecimal fiber;

        private BigDecimal sugar;

        private static NutritionItem of(Nutrition nutrition) {
            NutritionItem converted = new NutritionItem();
            converted.setCalories(nutrition.getCalories());
            converted.setFat(nutrition.getFat());
            converted.setSaturatedFat(nutrition.getSaturatedFat());
            converted.setTransFat(nutrition.getTransFat());
            converted.setSodium(nutrition.getSodium());
            converted.setCarbohydrates(nutrition.getCarbohydrates());
            converted.setProtein(nutrition.getProtein());
            converted.setFiber(nutrition.getFiber());
            converted.setSugar(nutrition.getSugar());
            return converted;
        }
    }

    @Data
    private static class IngredientItem {

        private BigDecimal purineAmount;

        private static IngredientItem of(Ingredient ingredient) {
            IngredientItem converted = new IngredientItem();
            converted.setPurineAmount(ingredient.getPurineAmount());
            return converted;
        }
    }
}
