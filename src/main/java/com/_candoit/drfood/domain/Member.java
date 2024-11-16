package com._candoit.drfood.domain;

import com._candoit.drfood.domain.enums.DietControl;
import com._candoit.drfood.domain.enums.ExerciseStatus;
import com._candoit.drfood.domain.enums.Gender;
import com._candoit.drfood.domain.enums.UserDisease;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login_id")
    private String loginId;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private UserDisease userDisease;

    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int age;

    private String height;

    private String weight;

    @Enumerated(EnumType.STRING)
    private ExerciseStatus exerciseStatus;

    private String userAddress;

    @Enumerated(EnumType.STRING)
    private DietControl dietControl;

    @Column(precision = 10, scale = 2)
    private BigDecimal dailyEnergy;

}
