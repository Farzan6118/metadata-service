package com.nesstiff.reference.metadataservice.service.impl;

import com.nesstiff.reference.metadataservice.entity.Bank;
import com.nesstiff.reference.metadataservice.repository.BankRepository;
import com.nesstiff.reference.metadataservice.service.BankService;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl extends BaseServiceImpl<BankRepository, Bank, Integer> implements BankService {

    public BankServiceImpl(BankRepository repository) {
        super(repository);
    }
}
