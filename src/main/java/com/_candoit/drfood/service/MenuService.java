package com._candoit.drfood.service;

import com._candoit.drfood.domain.Store;
import com._candoit.drfood.global.enums.ReturnCode;
import com._candoit.drfood.global.exception.DrFoodLogicException;
import com._candoit.drfood.domain.Ingredient;
import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.domain.Nutrition;
import com._candoit.drfood.repository.IngredientRepository;
import com._candoit.drfood.repository.MenuRepository;
import com._candoit.drfood.repository.NutritionRepository;
import com._candoit.drfood.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {

    private final MenuRepository menuRepository;
    private final NutritionRepository nutritionRepository;
    private final IngredientRepository ingredientRepository;
    private final StoreRepository storeRepository;

    public Page<Menu> findMenusByStoreId(Long storeId, Pageable pageable) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
        return menuRepository.findMenusByStore(store, pageable);
    }

    public Page<Menu> findMenusByStore(Store store, Pageable pageable) {
        return menuRepository.findMenusByStore(store, pageable);
    }

    public Menu getMenu(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
    }

    public Nutrition getNutrition(Menu menu) {
        return nutritionRepository.findByMenu(menu).orElseThrow(() -> new DrFoodLogicException(ReturnCode.NOT_FOUND_ENTITY));
    }

    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

    public BigDecimal getPurineByIngredient(Menu menu) {
        List<Ingredient> ingredients = ingredientRepository.findByMenu(menu);
        BigDecimal totalPurineAmount = BigDecimal.ZERO;
        for (Ingredient ingredient : ingredients) {
            totalPurineAmount = totalPurineAmount.add(ingredient.getPurine());
        }
        return totalPurineAmount;
    }
}
