package com._candoit.drfood.menu.repository;

import com._candoit.drfood.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
