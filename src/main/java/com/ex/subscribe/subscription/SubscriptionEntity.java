package com.ex.subscribe.subscription;

import com.ex.subscribe.notification.NotificationEntity;
import com.ex.subscribe.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private NotificationEntity notificationId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "start_date", nullable = false)
    private OffsetDateTime startDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "auto_renewal_cycle", length = 20)
    private AutoRenewalCycle autoRenewalCycle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SubscriptionStatus status;

    @Column(name = "auto_payment")
    private Boolean autoPayment;

    @Column(name = "notify_status")
    private Boolean notifyStatus;

    @Column(name = "is_custom", nullable = false)
    private Boolean isCustom;

    @Column(name = "custom_service_period")
    private Integer customServicePeriod;
}
