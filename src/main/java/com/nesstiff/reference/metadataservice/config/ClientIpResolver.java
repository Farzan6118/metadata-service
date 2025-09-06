package com.nesstiff.reference.metadataservice.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientIpResolver {
    @Value("${auth.use-application-in-local:true}")
    private boolean useLocalApplication;

    public String resolve(HttpServletRequest request) {
        if (useLocalApplication) {
            return request.getRemoteAddr();
        }

        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }

        String realIp = request.getHeader("X-Real-IP");
        return (realIp != null && !realIp.isEmpty()) ? realIp : request.getRemoteAddr();
    }
}
