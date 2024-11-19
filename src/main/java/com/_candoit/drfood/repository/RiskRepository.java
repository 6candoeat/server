package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Member;
import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.domain.Risk;
import com._candoit.drfood.domain.Store;
import com._candoit.drfood.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Integer> {

    @Query("SELECT r FROM Risk r WHERE r.member = :member AND r.menu.store = :store")
    List<Risk> findAllByMemberAndStore(@Param("member") Member member, @Param("store") Store store);

    @Query("SELECT r FROM Risk r WHERE r.member = :member AND r.menu.store.category = :category")
    List<Risk> findAllByMemberAndCategory(@Param("member") Member member,@Param("category")  Category category);

    @Query("SELECT r FROM Risk r WHERE r.member = :member AND r.riskLevel <> 'HIGH_RISK'")
    List<Risk> findMenusByMemberAndExcludeHighRisk(@Param("member") Member member);

}
