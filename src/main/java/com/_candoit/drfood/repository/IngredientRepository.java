package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Ingredient;
import com._candoit.drfood.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByMenu(Menu menu);
}
