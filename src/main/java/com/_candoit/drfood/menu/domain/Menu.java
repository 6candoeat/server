package com._candoit.drfood.menu.domain;

import com._candoit.drfood.global.DateTimeEntity;
import com._candoit.drfood.global.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Menu extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String menuName;

    private String description;

    private int price;

    private String menuImageUrl;

    private LocalDateTime deletedAt;

    public void update(MenuUpdateParam param) {
        this.category = param.getCategory();
        this.menuName = param.getMenuName();
        this.price = param.getPrice();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
