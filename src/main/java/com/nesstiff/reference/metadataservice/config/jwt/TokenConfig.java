package com.nesstiff.reference.metadataservice.config.jwt;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class TokenConfig {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 128;
    private static final int GCM_TAG_LENGTH = 128;
    private static final int GCM_IV_LENGTH = 12;
    private static final String SECRET_KEY = "1234567890123456";
    private final SecretKey secretKey;


    public TokenConfig() {

        secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), 0, 16, ALGORITHM);

    }

    public String encryptToken(String token) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);


        byte[] iv = new byte[GCM_IV_LENGTH];

        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);


        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

        byte[] encryptedToken = cipher.doFinal(token.getBytes());

        byte[] combined = new byte[iv.length + encryptedToken.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedToken, 0, combined, iv.length, encryptedToken.length);


        return Base64.getEncoder().encodeToString(combined);
    }

    public String decryptToken(String saltedToken) throws Exception {
        byte[] combined = Base64.getDecoder().decode(saltedToken);


        byte[] iv = new byte[GCM_IV_LENGTH];
        System.arraycopy(combined, 0, iv, 0, iv.length);

        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);


        byte[] encryptedToken = new byte[combined.length - GCM_IV_LENGTH];
        System.arraycopy(combined, GCM_IV_LENGTH, encryptedToken, 0, encryptedToken.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

        byte[] decryptedToken = cipher.doFinal(encryptedToken);

        return new String(decryptedToken);
    }
}
