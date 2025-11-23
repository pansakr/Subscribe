package com.ex.subscribe.order;

import com.ex.common.BaseEntity;
import com.ex.subscribe.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "order_no", nullable = false)
    private String orderNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Column(name = "order_at", nullable = false)
    private OffsetDateTime orderAt;

    @Column(name = "paid_at")
    private OffsetDateTime paidAt;

}
