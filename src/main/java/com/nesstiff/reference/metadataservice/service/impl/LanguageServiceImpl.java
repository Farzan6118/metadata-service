package com.nesstiff.reference.metadataservice.service.impl;

import com.nesstiff.reference.metadataservice.entity.Language;
import com.nesstiff.reference.metadataservice.entity.Region;
import com.nesstiff.reference.metadataservice.repository.LanguageRepository;
import com.nesstiff.reference.metadataservice.repository.RegionRepository;
import com.nesstiff.reference.metadataservice.service.LanguageService;
import com.nesstiff.reference.metadataservice.service.RegionService;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl extends BaseServiceImpl<LanguageRepository, Language, Integer> implements LanguageService {

    public LanguageServiceImpl(LanguageRepository repository) {
        super(repository);
    }
}
