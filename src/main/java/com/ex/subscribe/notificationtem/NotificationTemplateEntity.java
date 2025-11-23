package com.ex.subscribe.notificationtem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notification_templates")
@Getter
@Setter
public class NotificationTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_template_id")
    private Long id;

    @Column(nullable = false)
    private String message;
}
