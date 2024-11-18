package com._candoit.drfood.repository;

import com._candoit.drfood.domain.Member;
import com._candoit.drfood.domain.Menu;
import com._candoit.drfood.domain.Order;
import com._candoit.drfood.enums.UserDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Menu> findByMenu(Menu menu);

    // 1. 같은 질병을 가진 사용자들의 특정 메뉴 주문 횟수 (최근 6개월)
    @Query("""
                SELECT COUNT(o) 
                FROM Order o 
                JOIN o.member m 
                WHERE m.userDisease = :userDisease
                AND o.menu = :menu
                AND o.createdAt >= :sixMonthsAgo
            """)
    Long countOrdersByDiseaseAndMenuInLastSixMonths(
            @Param("userDisease") UserDisease userDisease,
            @Param("menu") Menu menu,
            @Param("sixMonthsAgo") LocalDateTime sixMonthsAgo
    );

    // 2. 같은 질병을 가진 사용자들의 총 주문 수
    @Query("""
                SELECT COUNT(o) 
                FROM Order o 
                JOIN o.member m 
                WHERE m.userDisease = :userDisease
                AND o.createdAt >= :sixMonthsAgo
            """)
    Long countTotalOrdersByDisease(@Param("userDisease") UserDisease userDisease, @Param("sixMonthsAgo") LocalDateTime sixMonthsAgo);

    // 3. 현재 로그인한 사용자의 특정 메뉴 주문 횟수
    @Query("""
                SELECT COUNT(o) 
                FROM Order o 
                WHERE o.member = :member 
                AND o.menu = :menu
            """)
    Long countOrdersByMemberAndMenu(
            @Param("member") Member member,
            @Param("menu") Menu menu
    );
    Long countByMember(Member member);
    Long countByMenu(Menu menu);
}