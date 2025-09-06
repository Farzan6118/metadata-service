package com.nesstiff.reference.metadataservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class DimDateOccasion extends BaseEntity<Integer> {

    private LocalDate dateKey;
    private Boolean isHoliday;

}
