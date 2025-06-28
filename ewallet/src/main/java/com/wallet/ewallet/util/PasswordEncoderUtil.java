package com.wallet.ewallet.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123"; // change if needed
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("BCrypt Encoded Password: " + encodedPassword);
    }
}
