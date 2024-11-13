package com._candoit.drfood.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "userId")
    private Long id;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private DiseaseStatus userDisease;

    private String phoneNum;

    private String height;

    private String weight;

    @Enumerated(EnumType.STRING)
    private ExerciseStatus exerciseStatus;

    private String address;

    @Enumerated(EnumType.STRING)
    private DietControl dietControl;

    @Column(precision = 10, scale = 2)
    private BigDecimal dailyEnergy;

}
