package com.wallet.ewallet.repository;

import com.wallet.ewallet.entity.Wallet;
import com.wallet.ewallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
