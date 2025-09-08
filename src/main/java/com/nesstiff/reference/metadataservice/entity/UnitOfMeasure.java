package com.nesstiff.reference.metadataservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
public class UnitOfMeasure extends BaseEntity<Integer> {

    @Nationalized
    @Column(nullable = false)
    private String ScientificName;

    private String logo;
}
