package com._candoit.drfood.domain;

import com._candoit.drfood.domain.enums.Category;
import com._candoit.drfood.global.DateTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "store")
@Getter @Setter
public class Store extends DateTimeEntity {

    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    private String storeName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String storePictureUrl;

    private String phone;

    private LocalDateTime deletedAt;

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
