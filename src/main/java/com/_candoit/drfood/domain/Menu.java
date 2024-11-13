package com._candoit.drfood.domain;

import com._candoit.drfood.domain.enums.Category;
import com._candoit.drfood.global.DateTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "menu")
@Getter @Setter
public class Menu extends DateTimeEntity {

    @Id @GeneratedValue
    @Column(name = "menu_id")
    private Long menuId;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String menuName;

    private String menuImageUrl;

    private String description;

    private int price;

    private LocalDateTime deletedAt;

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
