package com.ex.subscribe.user.entity;

import com.ex.subscribe.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", uniqueConstraints =
        {@UniqueConstraint(name = "uq_users_provider_provider_id", columnNames = {"provider", "provider_id"})})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column
    private String password;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String address;

    @Column
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;      // todo : 유저 한명이 권한 여러개 가질 수 있게 변경

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ProviderType provider;

    @Column(name = "provider_id", length = 100)
    private String providerId;

    public UserEntity(String email, String password, String name, String address, String phone, UserRole role, UserStatus status){
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public UserEntity(String email, UserRole userRole, UserStatus status, ProviderType provider, String providerId){
        this.email = email;
        this.role = userRole;
        this.status = status;
        this.provider = provider;
        this.providerId = providerId;
    }
}
