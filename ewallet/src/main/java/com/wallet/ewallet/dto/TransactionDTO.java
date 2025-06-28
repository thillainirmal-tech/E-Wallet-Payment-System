package com.wallet.ewallet.dto;

import com.wallet.ewallet.entity.Transaction;
import com.wallet.ewallet.entity.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private Double amount;
    private String description;
    private LocalDateTime timestamp;
    private TransactionType type;

    // Constructor to convert from Entity to DTO
    public TransactionDTO(Transaction txn) {
        this.id = txn.getId();
        this.amount = txn.getAmount();
        this.description = txn.getDescription();
        this.timestamp = txn.getTimestamp();
        this.type = txn.getType();
    }
}
