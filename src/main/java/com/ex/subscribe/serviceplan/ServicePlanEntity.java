package com.ex.subscribe.serviceplan;

import com.ex.subscribe.user.UserEntity;
import com.ex.subscribe.item.ItemEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "service_plans")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServicePlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_plan_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemId;

    @Column(length = 50)
    private String name;

    @Column(name = "paid_at", nullable = false)
    private OffsetDateTime paidAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ServicePlanStatus status;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

}
