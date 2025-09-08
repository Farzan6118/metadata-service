package com.nesstiff.reference.metadataservice.config.jwt;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class JwtAuthenticationMapper {

    /**
     * تبدیل JWT به Authentication token با استخراج نقش‌ها و نام کاربری.
     */
    public JwtAuthenticationToken toAuthenticationToken(@NonNull Jwt jwt) {

        String principalName = extractPrincipalName(jwt);

        Set<GrantedAuthority> authorities = Stream.concat(
                        extractStandardAuthorities(jwt).stream(),
                        extractResourceRoles(jwt).stream()
                )
                .collect(Collectors.toSet());

        if (isTokenExpired(jwt)) {
            log.warn("JWT token for principal {} is expired", principalName);
        }

        return new JwtAuthenticationToken(jwt, authorities, principalName);
    }

    private String extractPrincipalName(@NonNull Jwt jwt) {
        return Optional.ofNullable(jwt.getClaimAsString("preferred_username"))
                .orElse(jwt.getSubject());
    }

    private Collection<GrantedAuthority> extractStandardAuthorities(@NonNull Jwt jwt) {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        return converter.convert(jwt);
    }

    private Collection<GrantedAuthority> extractResourceRoles(@NonNull Jwt jwt) {

        Map<String, Object> resourceAccess = Optional.ofNullable(jwt.getClaimAsMap("resource_access"))
                .orElse(Map.of());

        return resourceAccess.entrySet().stream()
                .flatMap(entry -> {
                    String resourceId = entry.getKey();
                    Map<String, Object> resource = castToMap(entry.getValue());
                    Collection<String> roles = castToCollection(resource.get("roles"));
                    return roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role));
                })
                .collect(Collectors.toSet());
    }

    private boolean isTokenExpired(@NonNull Jwt jwt) {
        Instant expiresAt = jwt.getExpiresAt();
        return expiresAt != null && expiresAt.isBefore(Instant.now());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> castToMap(Object obj) {
        return obj instanceof Map ? (Map<String, Object>) obj : Map.of();
    }

    @SuppressWarnings("unchecked")
    private Collection<String> castToCollection(Object obj) {
        return obj instanceof Collection ? (Collection<String>) obj : Collections.emptyList();
    }
}
