package com._candoit.drfood.domain;

import com._candoit.drfood.global.DateTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Nutrition extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutritionId;

    @OneToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private BigDecimal calories;

    private BigDecimal fat;

    private BigDecimal saturatedFat;

    private BigDecimal transFat;

    private BigDecimal sodium;

    private BigDecimal carbohydrates;

    private BigDecimal protein;

    private BigDecimal fiber;

    private BigDecimal sugar;
}
