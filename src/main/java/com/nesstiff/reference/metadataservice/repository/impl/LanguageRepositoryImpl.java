package com.nesstiff.reference.metadataservice.repository.impl;

import com.nesstiff.reference.metadataservice.entity.Language;
import com.nesstiff.reference.metadataservice.entity.Region;
import com.nesstiff.reference.metadataservice.repository.LanguageRepository;
import com.nesstiff.reference.metadataservice.repository.RegionRepository;
import com.nesstiff.reference.metadataservice.repository.jpa.LanguageJpa;
import com.nesstiff.reference.metadataservice.repository.jpa.RegionJpa;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageRepositoryImpl extends BaseRepositoryImpl<LanguageJpa, Language, Integer> implements LanguageRepository {

    public LanguageRepositoryImpl(LanguageJpa jpaRepository) {
        super(jpaRepository);
    }
}
