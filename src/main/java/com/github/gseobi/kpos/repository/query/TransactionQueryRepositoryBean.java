package com.github.gseobi.kpos.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionQueryRepositoryBean extends TransactionQueryRepositoryImpl {
    public TransactionQueryRepositoryBean(JPAQueryFactory queryFactory) {
        super(queryFactory);
    }
}
