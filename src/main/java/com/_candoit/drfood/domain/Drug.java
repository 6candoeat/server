package com._candoit.drfood.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "drug")
@Getter @Setter
public class Drug {

    @Id
    @Column(name = "drug_code")
    private Long drugCode;

    private String drugName;

    private String companyName;

    private String drugCategory;

    private String diseaseCategory;
}
