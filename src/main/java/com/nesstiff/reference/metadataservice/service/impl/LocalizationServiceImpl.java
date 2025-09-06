package com.nesstiff.reference.metadataservice.service.impl;


import com.nesstiff.reference.metadataservice.service.LocalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String messageKey, Object[] args) {
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }

    @Override
    public String getMessage(String messageKey, Object[] args, Locale locale) {
        return messageSource.getMessage(messageKey, args, locale);
    }

}
