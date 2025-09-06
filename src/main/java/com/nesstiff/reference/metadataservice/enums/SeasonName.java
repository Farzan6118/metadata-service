package com.nesstiff.reference.metadataservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum SeasonName {
    SPRING(1, "بهار", 3),
    SUMMER(2, "تابستان", 6),
    AUTUMN(3, "پاییز", 9),
    WINTER(4, "زمستان", 12);

    private final Integer id;
    private final String persianName;
    private final Integer startMonth;

    public static Optional<SeasonName> findById(int id) {
        for (SeasonName seasonName : SeasonName.values()) {
            if (seasonName.id == id) {
                return Optional.of(seasonName);
            }
        }
        return Optional.empty();
    }

}