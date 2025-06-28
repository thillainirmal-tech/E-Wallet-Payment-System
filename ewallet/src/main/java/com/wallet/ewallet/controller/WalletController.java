package com.wallet.ewallet.controller;

import com.wallet.ewallet.dto.WalletResponseDto;
import com.wallet.ewallet.entity.Wallet;
import com.wallet.ewallet.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<WalletResponseDto> createWallet(Authentication authentication) {
        try {
            String username = authentication.getName();
            Wallet wallet = walletService.createWallet(username);
            WalletResponseDto responseDto = new WalletResponseDto(wallet.getId(), wallet.getBalance());
            logger.info("Wallet created for user: {}", username);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            logger.error("Error creating wallet: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<WalletResponseDto> getBalance(Authentication authentication) {
        try {
            String username = authentication.getName();
            double balance = walletService.getBalance(username);
            Wallet wallet = walletService.getWalletByUsername(username); // assume this method exists
            WalletResponseDto responseDto = new WalletResponseDto(wallet.getId(), balance);
            logger.info("Balance retrieved for user: {}", username);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            logger.error("Error retrieving balance: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
