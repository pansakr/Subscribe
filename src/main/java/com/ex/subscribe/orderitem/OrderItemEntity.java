package com.ex.subscribe.orderitem;

import com.ex.common.BaseEntity;
import com.ex.subscribe.order.OrderEntity;
import com.ex.subscribe.item.ItemEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderItemStatus status;
}
