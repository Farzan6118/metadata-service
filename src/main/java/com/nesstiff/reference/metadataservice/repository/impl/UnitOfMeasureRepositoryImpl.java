package com.nesstiff.reference.metadataservice.repository.impl;

import com.nesstiff.reference.metadataservice.entity.UnitOfMeasure;
import com.nesstiff.reference.metadataservice.repository.UnitOfMeasureRepository;
import com.nesstiff.reference.metadataservice.repository.jpa.BankJpa;
import com.nesstiff.reference.metadataservice.repository.jpa.UnitOfMeasureJpa;
import org.springframework.stereotype.Repository;

@Repository
public class UnitOfMeasureRepositoryImpl extends BaseRepositoryImpl<UnitOfMeasureJpa, UnitOfMeasure, Integer> implements UnitOfMeasureRepository {

    public UnitOfMeasureRepositoryImpl(UnitOfMeasureJpa jpaRepository) {
        super(jpaRepository);
    }
}
