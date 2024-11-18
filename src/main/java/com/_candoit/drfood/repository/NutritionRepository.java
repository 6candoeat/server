package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.domain.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
    Optional<Nutrition> findByMenu(Menu menu);
}
