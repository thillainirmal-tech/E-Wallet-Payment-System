package com.wallet.ewallet.service;

import com.wallet.ewallet.entity.*;
import com.wallet.ewallet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WalletRepository walletRepo;

    // ✅ Deposit
    public Transaction deposit(String username, double amount) {
        Wallet wallet = getWalletByUsername(username);
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepo.save(wallet);

        Transaction txn = createTransaction(wallet, TransactionType.DEPOSIT, amount, "Deposited funds");
        return transactionRepo.save(txn);
    }

    // ✅ Withdraw
    public Transaction withdraw(String username, double amount) {
        Wallet wallet = getWalletByUsername(username);

        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        wallet.setBalance(wallet.getBalance() - amount);
        walletRepo.save(wallet);

        Transaction txn = createTransaction(wallet, TransactionType.WITHDRAW, amount, "Withdrawn funds");
        return transactionRepo.save(txn);
    }

    // ✅ Transfer
    public Transaction transfer(String senderUsername, String recipientUsername, double amount) {
        Wallet senderWallet = getWalletByUsername(senderUsername);
        Wallet recipientWallet = getWalletByUsername(recipientUsername);

        if (senderWallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance to transfer");
        }

        senderWallet.setBalance(senderWallet.getBalance() - amount);
        recipientWallet.setBalance(recipientWallet.getBalance() + amount);

        walletRepo.save(senderWallet);
        walletRepo.save(recipientWallet);

        Transaction txn = createTransaction(senderWallet, TransactionType.TRANSFER, amount,
                "Transferred to " + recipientUsername);
        return transactionRepo.save(txn);
    }

    // ✅ Transaction History
    public List<Transaction> getTransactionHistory(String username) {
        Wallet wallet = getWalletByUsername(username);
        return transactionRepo.findByWalletId(wallet.getId());
    }

    // ✅ Get Wallet Balance
    public Double getBalance(String username) {
        Wallet wallet = getWalletByUsername(username);
        return wallet.getBalance();
    }

    // ✅ Helper: Get wallet from username
    private Wallet getWalletByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return walletRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    // ✅ Helper: Create transaction
    private Transaction createTransaction(Wallet wallet, TransactionType type, double amount, String description) {
        return Transaction.builder()
                .wallet(wallet)
                .type(type)
                .amount(amount)
                .description(description)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
