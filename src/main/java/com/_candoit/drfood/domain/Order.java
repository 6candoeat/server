package com._candoit.drfood.domain;

import com._candoit.drfood.enums.OrderStatus;
import com._candoit.drfood.enums.PaymentMethod;
import com._candoit.drfood.global.DateTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends DateTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member; // 주문 회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private int totalPrice;

    private String requests;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
