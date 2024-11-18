package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Store;
import com._candoit.drfood.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Page<Store> findByCategory(Category category, Pageable pageable);
}
