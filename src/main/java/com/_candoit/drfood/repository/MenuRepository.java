package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findMenusByStore(Store store, Pageable pageable);

    List<Menu> findMenusByStore(Store store);
}
