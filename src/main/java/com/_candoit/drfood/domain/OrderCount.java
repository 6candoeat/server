package com._candoit.drfood.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class OrderCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderCountId;

    @ManyToOne
    private Menu menu;

    private int diabetesCount;

    private int goutCount;

    private int hyperExtensionCount;
}
