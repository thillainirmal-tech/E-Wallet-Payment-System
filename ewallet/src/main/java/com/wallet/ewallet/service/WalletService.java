package com.wallet.ewallet.service;

import com.wallet.ewallet.entity.User;
import com.wallet.ewallet.entity.Wallet;
import com.wallet.ewallet.repository.UserRepository;
import com.wallet.ewallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private UserRepository userRepo;

    // ✅ Create a new wallet for user
    public Wallet createWallet(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (walletRepo.findByUser(user).isPresent()) {
            throw new RuntimeException("Wallet already exists for this user.");
        }

        Wallet wallet = Wallet.builder()
                .user(user)
                .balance(0.0)
                .build();

        return walletRepo.save(wallet);
    }

    // ✅ Get wallet balance
    public double getBalance(String username) {
        Wallet wallet = getWalletByUsername(username);
        return wallet.getBalance();
    }

    // ✅ Get full wallet details by username (used in controller or other services)
    public Wallet getWalletByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return walletRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }
}
