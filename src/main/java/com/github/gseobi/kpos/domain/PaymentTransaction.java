package com.github.gseobi.kpos.domain;

import com.github.gseobi.kpos.domain.enumtype.PaymentMethod;
import com.github.gseobi.kpos.domain.enumtype.TxStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "payment_transactions")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class PaymentTransaction extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 60)
    private String txId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    private Terminal terminal;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false, length = 10)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TxStatus status;

    @Column(length = 60)
    private String buyerId;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private OffsetDateTime requestedAt;

    private OffsetDateTime approvedAt;

    public void approve(OffsetDateTime approvedAt) {
        this.status = TxStatus.APPROVED;
        this.approvedAt = approvedAt;
    }
}
