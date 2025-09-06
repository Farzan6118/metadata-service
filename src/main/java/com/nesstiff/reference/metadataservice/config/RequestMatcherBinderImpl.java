package com.nesstiff.reference.metadataservice.config;


import org.springframework.stereotype.Component;

@Component
public class RequestMatcherBinderImpl implements RequestMatcherBinder {

    @Override
    public String[] getPermitsAllUrls() {
        return new String[]{
                "/v2/api-docs",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/v3/api-docs/**",
                "/auth/v3/api-docs",
                "/swagger-ui/**",
                "/actuator/**",
                "/httpexchanges",
                "/bezkoder-api-docs",
                "/v3/api-docs",
                "/bezkoder-documentation",
                "/api/v1/auth/**",
                "/api/v1/otp/forget-password/**",
                "/api/v1/otp/email"
        };
    }
}
