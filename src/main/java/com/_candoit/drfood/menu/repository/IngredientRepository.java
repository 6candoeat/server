package com._candoit.drfood.menu.repository;

import com._candoit.drfood.menu.domain.Ingredient;
import com._candoit.drfood.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByMenu(Menu menu);
}