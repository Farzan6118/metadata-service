package com.nesstiff.reference.metadataservice.repository.impl;

import com.nesstiff.reference.metadataservice.entity.Bank;
import com.nesstiff.reference.metadataservice.entity.Region;
import com.nesstiff.reference.metadataservice.repository.RegionRepository;
import com.nesstiff.reference.metadataservice.repository.jpa.RegionJpa;
import org.springframework.stereotype.Repository;

@Repository
public class RegionRepositoryImpl extends BaseRepositoryImpl<RegionJpa, Region, Integer> implements RegionRepository {

    public RegionRepositoryImpl(RegionJpa jpaRepository) {
        super(jpaRepository);
    }
}
