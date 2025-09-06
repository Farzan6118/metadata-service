package com.nesstiff.reference.metadataservice.service.impl;

import com.nesstiff.reference.metadataservice.entity.Bank;
import com.nesstiff.reference.metadataservice.repository.BankRepository;
import com.nesstiff.reference.metadataservice.service.CurrencyService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl extends BaseServiceImpl<BankRepository, Bank, Integer> implements CurrencyService {

    public CurrencyServiceImpl(BankRepository repository) {
        super(repository);
    }
}
