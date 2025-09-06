package com.nesstiff.reference.metadataservice.config;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EncryptedNimbusJwtDecoder implements JwtDecoder {

    private final static Map<String, NimbusJwtDecoder> NIMBUS_JWT_DECODER_MAP = new ConcurrentHashMap<>();

    private final String keycloakServerUrl;

    private final String realmId;
    private final TokenConfig tokenConfig;

    public EncryptedNimbusJwtDecoder(String keycloakServerUrl, String realmId, TokenConfig tokenConfig) {
        this.keycloakServerUrl = keycloakServerUrl;
        this.realmId = realmId;
        this.tokenConfig = tokenConfig;
    }

    @Override
    public Jwt decode(String token) throws JwtException {

        final String decodedToken;
        try {
            decodedToken = tokenConfig.decryptToken(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return getDecoder().decode(decodedToken);
    }

    private NimbusJwtDecoder getDecoder() {

        final String dynamicIssuerUri = getDynamicIssuerUri();
        return NIMBUS_JWT_DECODER_MAP.computeIfAbsent(dynamicIssuerUri, uri -> NimbusJwtDecoder.withJwkSetUri(uri).build());
    }

    private String getDynamicIssuerUri() {

        return String.format("%s/realms/%s/protocol/openid-connect/certs", keycloakServerUrl, realmId);

    }
}
