package com.nesstiff.reference.metadataservice.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilter {

    private final TokenConfig tokenConfig;

    @Bean
    public JwtDecoder jwtDecoder(@Value("${keycloak.auth-server-url}") String keycloakServerUrl, @Value("${keycloak.instance.realm-id}") String realmId) {
        return new EncryptedNimbusJwtDecoder(keycloakServerUrl, realmId, tokenConfig);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RequestMatcherBinder requestMatcherBinder, JwtDecoder jwtDecoder, JwtConverter jwtConverter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    String[] permitsAllUrls = requestMatcherBinder.getPermitsAllUrls();
                    if (permitsAllUrls != null && permitsAllUrls.length > 0) {
                        request.requestMatchers(permitsAllUrls).permitAll();
                    }
                    request.anyRequest().authenticated();
                })
//                .addFilterBefore(rateLimitFilter, AuthorizationFilter.class)
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder)
                                .jwtAuthenticationConverter(jwtConverter)));
        return http.build();
    }

}
