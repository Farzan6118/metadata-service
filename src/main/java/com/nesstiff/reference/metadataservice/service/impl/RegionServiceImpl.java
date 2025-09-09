package com.nesstiff.reference.metadataservice.service.impl;

import com.nesstiff.reference.metadataservice.entity.Region;
import com.nesstiff.reference.metadataservice.repository.RegionRepository;
import com.nesstiff.reference.metadataservice.service.RegionService;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl extends BaseServiceImpl<RegionRepository, Region, Integer> implements RegionService {

    public RegionServiceImpl(RegionRepository repository) {
        super(repository);
    }
}
