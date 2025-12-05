package com.ex.subscribe.global.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.OffsetDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseTimeEntity{

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "modified_at")
    private Instant modifiedDate;

}
