package com.nesstiff.reference.metadataservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum WeekName {
    SATURDAY(1, "saturday"),
    SUNDAY(1, "sunday"),
    MONDAY(1, "monday"),
    TUESDAY(1, "tuesday"),
    WEDNESDAY(1, "wednesday"),
    THURSDAY(1, "thursday"),
    FRIDAY(1, "friday");

    private final Integer id;
    private final String persianName;

    public static Optional<WeekName> findById(int id) {
        for (WeekName weekName : WeekName.values()) {
            if (weekName.id == id) {
                return Optional.of(weekName);
            }
        }
        return Optional.empty();
    }

}