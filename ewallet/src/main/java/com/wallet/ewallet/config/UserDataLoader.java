package com.wallet.ewallet.config;

import com.wallet.ewallet.entity.User;
import com.wallet.ewallet.entity.Wallet;
import com.wallet.ewallet.repository.UserRepository;
import com.wallet.ewallet.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDataLoader {

    @Bean
    CommandLineRunner loadUserData(UserRepository userRepository, WalletRepository walletRepository) {
        return args -> {
            createUserIfNotExists(userRepository, walletRepository,
                    "admin", "$2a$10$Dl6dZcDcNcLTs9CKzzyzU.ZJz8BQ01H5qZfIoXkGMUlw6duDPKMNO", "admin@email.com", "ADMIN");

            createUserIfNotExists(userRepository, walletRepository,
                    "user", "$2a$10$Dl6dZcDcNcLTs9CKzzyzU.ZJz8BQ01H5qZfIoXkGMUlw6duDPKMNO", "user@email.com", "USER");
        };
    }

    private void createUserIfNotExists(UserRepository userRepository, WalletRepository walletRepository,
                                       String username, String encodedPassword, String email, String role) {
        userRepository.findByUsername(username).ifPresentOrElse(
            user -> System.out.println("ℹ️ " + role + " user already exists: " + username),
            () -> {
                User user = User.builder()
                        .username(username)
                        .password(encodedPassword)
                        .email(email)
                        .role(role)
                        .build();
                userRepository.save(user);
                System.out.println("✅ " + role + " user inserted: " + username);

                Wallet wallet = Wallet.builder()
                        .user(user)
                        .balance(1000.0)
                        .build();
                walletRepository.save(wallet);
                System.out.println("✅ Wallet created for " + username);
            }
        );
    }
}
