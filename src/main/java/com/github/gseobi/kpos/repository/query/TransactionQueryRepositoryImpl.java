package com.github.gseobi.kpos.repository.query;

import com.github.gseobi.kpos.domain.QPaymentTransaction;
import com.github.gseobi.kpos.dto.tx.TransactionResponse;
import com.github.gseobi.kpos.dto.tx.TransactionSearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;

@RequiredArgsConstructor
public class TransactionQueryRepositoryImpl implements TransactionQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TransactionResponse> search(TransactionSearchCondition cond, Pageable pageable) {
        QPaymentTransaction tx = QPaymentTransaction.paymentTransaction;

        BooleanBuilder where = new BooleanBuilder();

        if (cond.getStoreId() != null) where.and(tx.store.id.eq(cond.getStoreId()));
        if (cond.getMethod() != null) where.and(tx.method.eq(cond.getMethod()));
        if (cond.getStatus() != null) where.and(tx.status.eq(cond.getStatus()));
        if (cond.getFrom() != null) where.and(tx.requestedAt.goe(cond.getFrom()));
        if (cond.getTo() != null) where.and(tx.requestedAt.loe(cond.getTo()));
        if (cond.getMinAmount() != null) where.and(tx.amount.goe(cond.getMinAmount()));
        if (cond.getMaxAmount() != null) where.and(tx.amount.loe(cond.getMaxAmount()));

        if (cond.getKeyword() != null && !cond.getKeyword().isBlank()) {
            String like = "%" + cond.getKeyword().trim() + "%";
            where.and(
                    tx.txId.likeIgnoreCase(like)
                            .or(tx.description.likeIgnoreCase(like))
                            .or(tx.buyerId.likeIgnoreCase(like))
            );
        }

        // Default Sort: requestedAt desc
        OrderSpecifier<?> order = tx.requestedAt.desc();

        var query = queryFactory
                .select(tx)
                .from(tx)
                .where(where)
                .orderBy(order);

        // total count
        NumberExpression<Long> countExpr = Expressions.numberTemplate(Long.class, "count({0})", tx.id);
        Long total = queryFactory
                .select(countExpr)
                .from(tx)
                .where(where)
                .fetchOne();

        long totalCount = (total == null) ? 0L : total;

        // content
        List<TransactionResponse> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(e -> TransactionResponse.builder()
                        .txId(e.getTxId())
                        .storeId(e.getStore().getId())
                        .terminalId(e.getTerminal() != null ? e.getTerminal().getId() : null)
                        .amount(e.getAmount())
                        .currency(e.getCurrency())
                        .method(e.getMethod())
                        .status(e.getStatus())
                        .buyerId(e.getBuyerId())
                        .description(e.getDescription())
                        .requestedAt(e.getRequestedAt())
                        .approvedAt(e.getApprovedAt())
                        .build())
                .toList();

        return new PageImpl<>(content, pageable, total);
    }
}
