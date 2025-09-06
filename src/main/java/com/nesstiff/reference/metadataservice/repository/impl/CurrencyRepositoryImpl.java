package com.nesstiff.reference.metadataservice.repository.impl;

import com.nesstiff.reference.metadataservice.entity.Currency;
import com.nesstiff.reference.metadataservice.repository.CurrencyRepository;
import com.nesstiff.reference.metadataservice.repository.jpa.CurrencyJpa;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyRepositoryImpl extends BaseRepositoryImpl<CurrencyJpa, Currency, Integer> implements CurrencyRepository {

    public CurrencyRepositoryImpl(CurrencyJpa jpaRepository) {
        super(jpaRepository);
    }
}
