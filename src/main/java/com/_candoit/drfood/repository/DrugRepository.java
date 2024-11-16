package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    // 약품명으로 질병 조회
    Drug findByDrugName(String drugName);
}
