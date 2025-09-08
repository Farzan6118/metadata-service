package com.nesstiff.reference.metadataservice.config;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuditorAwareConfig implements AuditorAware<UUID> {

    /**
     * UUID پیشفرض سیستم برای عملیات بدون کاربر لاگین.
     * مقدارش از application.properties یا application.yml خوانده می‌شود.
     */
    @Value("${system.default.uuid}")
    private UUID systemUuid;

    @Override
    public @NotNull Optional<UUID> getCurrentAuditor() {
        // اول تلاش می‌کنیم UUID کاربر فعلی را استخراج کنیم، اگر نبود fallback به systemUuid
        return extractUserUuid().or(() -> Optional.of(systemUuid));
    }

    /**
     * تلاش می‌کند UUID کاربر فعلی را از SecurityContextHolder استخراج کند.
     * اگر مقدار نامعتبر یا ناموجود بود، Optional.empty برمی‌گرداند.
     */
    private Optional<UUID> extractUserUuid() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getName)
                .flatMap(this::safeParseUuid);
    }

    /**
     * تبدیل امن String به UUID، در صورت نامعتبر بودن، Optional.empty برمی‌گرداند.
     */
    private Optional<UUID> safeParseUuid(String str) {
        try {
            return Optional.of(UUID.fromString(str));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}