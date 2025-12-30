package com.ex.subscribe.user.repository;

import com.ex.subscribe.user.entity.ProviderType;
import com.ex.subscribe.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByProviderAndProviderId(ProviderType providerType, String providerId);
}
