package com.nesstiff.reference.metadataservice.entity;

import com.nesstiff.reference.metadataservice.enums.RegionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Region extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    private RegionType regionType;
    @Column(length = 32, nullable = false)
    private String code;
    @Column(length = 16, nullable = false)
    private String name;
    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;
    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;
    @Column(nullable = false)
    private Integer levelId;

    private String countryCode;
    private String logo;


}
