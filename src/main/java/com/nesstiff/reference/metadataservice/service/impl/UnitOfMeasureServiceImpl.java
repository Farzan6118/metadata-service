package com.nesstiff.reference.metadataservice.service.impl;

import com.nesstiff.reference.metadataservice.entity.UnitOfMeasure;
import com.nesstiff.reference.metadataservice.repository.UnitOfMeasureRepository;
import com.nesstiff.reference.metadataservice.service.UnitOfMeasureService;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasureServiceImpl extends BaseServiceImpl<UnitOfMeasureRepository, UnitOfMeasure, Integer> implements UnitOfMeasureService {

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository) {
        super(repository);
    }
}
