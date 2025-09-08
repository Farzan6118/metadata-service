package com.nesstiff.reference.metadataservice.entity;

import com.nesstiff.reference.metadataservice.enums.ContinentName;
import com.nesstiff.reference.metadataservice.enums.RegionType;
import com.nesstiff.reference.metadataservice.enums.WeekName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

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

    private String nationality;
    @Column(nullable = false)
    private String countryCode;
    private String logo;
    private String flag;
    @Column(nullable = false)
    private Set<ContinentName> continents;
    @Column(nullable = false)
    @Min(0)
    private Integer population;
    private WeekName startOfWeek;


}
