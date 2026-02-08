package com.github.gseobi.kpos.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "terminals")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Terminal extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String terminalCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Store store;

    @Column(nullable = false, length = 20)
    private String status; // ACTIVE / INACTIVE
}
