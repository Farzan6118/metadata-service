package com.nesstiff.reference.metadataservice.repository.impl;


import com.nesstiff.reference.metadataservice.entity.BaseEntity;
import com.nesstiff.reference.metadataservice.repository.BaseRepository;
import com.nesstiff.reference.metadataservice.repository.jpa.BaseJpa;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<U extends BaseJpa<T, ID>,
        T extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<T, ID> {

    protected final U jpaRepository;

    public BaseRepositoryImpl(U jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public T save(T entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public Boolean existsById(ID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void saveAll(List<T> entities) {
        jpaRepository.saveAll(entities);
    }
}
