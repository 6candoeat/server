package com._candoit.drfood.menu.service;

import com._candoit.drfood.global.enums.ReturnCode;
import com._candoit.drfood.global.exception.DrFoodLogicException;
import com._candoit.drfood.domain.Ingredient;
import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.domain.Nutrition;
import com._candoit.drfood.menu.repository.IngredientRepository;
import com._candoit.drfood.menu.repository.MenuRepository;
import com._candoit.drfood.menu.repository.NutritionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {

    private final MenuRepository menuRepository;
    private final NutritionRepository nutritionRepository;
    private final IngredientRepository ingredientRepository;

    public Menu getMenu(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
    }

    public Nutrition getNutrition(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
        return nutritionRepository.findByMenu(menu).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
    }

    public Ingredient getIngredient(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
        return ingredientRepository.findByMenu(menu).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
    }
}
