package com.nesstiff.reference.metadataservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class DimDate extends BaseEntity<Integer> {

    private LocalDate dateKey;
    @Column(nullable = false)
    private Integer dateINTKey;
    private Integer persianDateKey;
    @Column(length = 10)
    private String persianDate;
    @ManyToOne
    private DimDayNameOfWeek gregorianDayNumberOfWeek;

    private Integer gregorianDayNumberOfMonth;

    private Integer gregorianDayNumberOfYear;

    private Integer gregorianWeekNumberOfYear;
    @ManyToOne
    private DimGregorianMonth gregorianMonthNumberOfYear;

    private Integer gregorianYear;

    private Integer persianDayNumberOfWeek;

    private Integer persianDayNumberOfMonth;

    private Integer persianWeekNumberOfYear;
    @ManyToOne
    private DimPersianMonth persianMonthNumberOfYear;

    private Integer persianYear;

    private Integer quarter;
    private LocalDateTime miladiDate;
    private Integer persianDayNumberOfWeekDesc;

    private Boolean isHoliday;

}
