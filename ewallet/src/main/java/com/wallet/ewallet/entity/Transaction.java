package com.wallet.ewallet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // DEPOSIT, WITHDRAW, TRANSFER

    private Double amount;

    private String description;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}
