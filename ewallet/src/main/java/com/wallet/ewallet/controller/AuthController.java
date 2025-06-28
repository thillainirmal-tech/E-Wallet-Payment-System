package com.wallet.ewallet.controller;

import com.wallet.ewallet.dto.AuthRequest;
import com.wallet.ewallet.dto.AuthResponse;
import com.wallet.ewallet.dto.RegisterRequest;
import com.wallet.ewallet.entity.User;
import com.wallet.ewallet.entity.Wallet;
import com.wallet.ewallet.repository.UserRepository;
import com.wallet.ewallet.repository.WalletRepository;
import com.wallet.ewallet.security.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            String jwt = jwtUtils.generateJwtToken(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(jwt));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Invalid username or password.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Error during authentication.");
        }
    }

    // ✅ Register Endpoint with Wallet Creation
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Username already exists.");
        }

        // ✅ Save user
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        user = userRepo.save(user); // Important to get managed user with ID

        // ✅ Create wallet and link with saved user
        Wallet wallet = Wallet.builder()
                .user(user)
                .balance(0.0)
                .build();
        walletRepo.save(wallet);

        return ResponseEntity.status(HttpStatus.CREATED).body("✅ User registered and wallet created successfully!");
    }
}
