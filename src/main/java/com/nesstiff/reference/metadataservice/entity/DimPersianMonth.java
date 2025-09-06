package com.nesstiff.reference.metadataservice.entity;

import com.nesstiff.reference.metadataservice.enums.SeasonName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@Entity
public class DimPersianMonth extends BaseEntity<Integer> {

    @Column(length = 16, nullable = false)
    private String MonthName;
    @Column(length = 16, nullable = false)
    private String HalfYearName;
    @Column(length = 16, nullable = false)
    private String QuarterYearName;
    @NotNull
    @Enumerated(EnumType.STRING)
    private SeasonName SeasonName;

}
