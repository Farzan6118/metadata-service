package com.nesstiff.reference.metadataservice.service;

import java.util.Locale;

public interface LocalizationService {

    String getMessage(String messageKey, Object[] args);

    String getMessage(String messageKey, Object[] args, Locale locale);

}
