package com.wallet.ewallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletResponseDto {
    private Long id;
    private double balance;
}
