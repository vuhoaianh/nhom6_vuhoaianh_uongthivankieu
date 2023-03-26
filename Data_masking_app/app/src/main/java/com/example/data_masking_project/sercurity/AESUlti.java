package com.example.data_masking_project.sercurity;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUlti {
    private static final String ALGORITHM = "AES";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static String encrypt(String value, String userKey) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        SecretKey key = generateKey(userKey, salt);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(value.getBytes());

        byte[] saltedEncryptedValue = new byte[salt.length + encryptedValue.length];
        System.arraycopy(salt, 0, saltedEncryptedValue, 0, salt.length);
        System.arraycopy(encryptedValue, 0, saltedEncryptedValue, salt.length, encryptedValue.length);

        return Base64.getEncoder().encodeToString(saltedEncryptedValue);
    }

    public static String decrypt(String value, String userKey) throws Exception {
        byte[] saltedEncryptedValue = Base64.getDecoder().decode(value.getBytes());
        byte[] salt = new byte[16];
        byte[] encryptedValue = new byte[saltedEncryptedValue.length - salt.length];
        System.arraycopy(saltedEncryptedValue, 0, salt, 0, salt.length);
        System.arraycopy(saltedEncryptedValue, salt.length, encryptedValue, 0, encryptedValue.length);

        SecretKey key = generateKey(userKey, salt);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue = cipher.doFinal(encryptedValue);

        return new String(decryptedValue);
    }

    private static SecretKey generateKey(String userKey, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(userKey.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
}
