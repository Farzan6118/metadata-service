package com.nesstiff.reference.metadataservice.config.security;


import org.springframework.stereotype.Component;

@Component
public class RequestMatcherBinderImpl implements RequestMatcherBinder {


    private static final String[] PERMIT_ALL_URLS = {
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
            "/configuration/ui",
            "/configuration/security",
            "/actuator/**",
            "/httpexchanges",
            "/bezkoder-api-docs",
            "/bezkoder-documentation",
            "/api/v1/auth/**",
            "/api/v1/otp/forget-password/**",
            "/api/v1/otp/email"
    };

    @Override
    public String[] getPermitsAllUrls() {
        return PERMIT_ALL_URLS;
    }
}
