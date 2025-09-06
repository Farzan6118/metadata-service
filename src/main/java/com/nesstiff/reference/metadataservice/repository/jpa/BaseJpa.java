package com.nesstiff.reference.metadataservice.repository.jpa;

import com.nesstiff.reference.metadataservice.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface BaseJpa<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
