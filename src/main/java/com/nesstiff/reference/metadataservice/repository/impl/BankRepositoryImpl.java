package com.nesstiff.reference.metadataservice.repository.impl;

import com.nesstiff.reference.metadataservice.entity.Bank;
import com.nesstiff.reference.metadataservice.repository.BankRepository;
import com.nesstiff.reference.metadataservice.repository.jpa.BankJpa;
import org.springframework.stereotype.Repository;

@Repository
public class BankRepositoryImpl extends BaseRepositoryImpl<BankJpa, Bank, Integer> implements BankRepository {

    public BankRepositoryImpl(BankJpa jpaRepository) {
        super(jpaRepository);
    }
}
