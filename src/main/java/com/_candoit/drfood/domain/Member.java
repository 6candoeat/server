package com._candoit.drfood.domain;

import com._candoit.drfood.domain.enums.DietControl;
import com._candoit.drfood.domain.enums.ExerciseStatus;
import com._candoit.drfood.domain.enums.UserDisease;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private UserDisease userDisease;

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
