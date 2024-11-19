package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Ingredient;
import com._candoit.drfood.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByMenu(Menu menu);
}
