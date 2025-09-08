package com.nesstiff.reference.metadataservice.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class EncryptedNimbusJwtDecoder implements JwtDecoder {

    // کش برای NimbusJwtDecoder بر اساس issuer-uri
    private static final Map<String, NimbusJwtDecoder> DECODER_CACHE = new ConcurrentHashMap<>();

    private final TokenConfig tokenConfig; // Bean inject می‌شود

    // مقادیری که از application.yml می‌آیند
    @Value("${keycloak.realm}")
    private String realmId;

    @Value("${keycloak.server-url}")
    private String keycloakServerUrl;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            // 1️⃣ decrypt کردن توکن رمزگذاری‌شده
            String decryptedToken = tokenConfig.decryptToken(token);

            // 2️⃣ decode واقعی با NimbusJwtDecoder
            return getDecoder().decode(decryptedToken);

        } catch (JwtException e) {
            throw e; // JwtException رو مستقیم پرتاب می‌کنیم
        } catch (Exception e) {
            throw new JwtException("Failed to decode JWT", e);
        }
    }

    private NimbusJwtDecoder getDecoder() {
        String jwkSetUri = getDynamicIssuerUri();

        // استفاده از کش برای جلوگیری از ایجاد دوباره decoder
        return DECODER_CACHE.computeIfAbsent(jwkSetUri,
                uri -> NimbusJwtDecoder.withJwkSetUri(uri).build()
        );
    }

    private String getDynamicIssuerUri() {
        return String.format("%s/realms/%s/protocol/openid-connect/certs",
                keycloakServerUrl, realmId);
    }
}