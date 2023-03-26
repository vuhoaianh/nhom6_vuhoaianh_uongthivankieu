package com.example.UserApi.util;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class GenerateKey {
    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int KEY_LENGTH = 32;
    private static final Set<String> GENERATED_KEYS = new HashSet<>();

    public static String generateRandomKey() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(KEY_LENGTH);

        while (true) {
            for (int i = 0; i < KEY_LENGTH; i++) {
                int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
                char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
                sb.append(randomChar);
            }

            String key = sb.toString();

            if (!GENERATED_KEYS.contains(key)) {
                GENERATED_KEYS.add(key);
                return key;
            }

            sb.setLength(0);
        }
    }
}
