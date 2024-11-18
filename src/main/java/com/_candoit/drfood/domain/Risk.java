package com._candoit.drfood.domain;

import com._candoit.drfood.enums.RiskLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "risk")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Risk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "risk_id")
    private Long riskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
}
