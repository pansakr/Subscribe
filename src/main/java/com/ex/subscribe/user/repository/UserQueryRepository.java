package com.ex.subscribe.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import static com.ex.subscribe.user.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean existsByEmail(String email) {

        return queryFactory
                .selectOne()
                .from(userEntity)
                .where(userEntity.email.eq(email))
                .fetchFirst() != null;
    }

    public boolean existsByPhone(String phone) {

        return queryFactory
                .selectOne()
                .from(userEntity)
                .where(userEntity.phone.eq(phone))
                .fetchFirst() != null;

    }
}