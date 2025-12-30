package com.ex.subscribe.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import static com.ex.subscribe.user.entity.QUserEntity.userEntity;


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