package com.wallet.ewallet.controller;

import com.wallet.ewallet.dto.TransactionDTO;
import com.wallet.ewallet.entity.Transaction;
import com.wallet.ewallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // ✅ Deposit (Safe return with DTO)
    @PostMapping("/deposit")
    public TransactionDTO deposit(@RequestBody Map<String, Double> request, Authentication authentication) {
        String username = authentication.getName();
        double amount = request.get("amount");
        Transaction transaction = transactionService.deposit(username, amount);
        return new TransactionDTO(transaction);
    }

    // ✅ Withdraw (Safe return with DTO)
    @PostMapping("/withdraw")
    public TransactionDTO withdraw(@RequestBody Map<String, Double> request, Authentication authentication) {
        String username = authentication.getName();
        double amount = request.get("amount");
        Transaction transaction = transactionService.withdraw(username, amount);
        return new TransactionDTO(transaction);
    }

    // ✅ Transfer (Safe return with DTO)
    @PostMapping("/transfer")
    public TransactionDTO transfer(@RequestBody Map<String, Object> request, Authentication authentication) {
        String senderUsername = authentication.getName();
        String recipientUsername = (String) request.get("recipientUsername");
        double amount = Double.parseDouble(request.get("amount").toString());
        Transaction transaction = transactionService.transfer(senderUsername, recipientUsername, amount);
        return new TransactionDTO(transaction);
    }

    // ✅ Transaction History (DTO-safe)
    @GetMapping("/history")
    public List<TransactionDTO> getHistory(Authentication authentication) {
        String username = authentication.getName();
        return transactionService.getTransactionHistory(username).stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ Get Wallet Balance
    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(Authentication authentication) {
        String username = authentication.getName();
        Double balance = transactionService.getBalance(username);
        return ResponseEntity.ok(balance);
    }
}
