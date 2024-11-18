package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Integer> {

}
