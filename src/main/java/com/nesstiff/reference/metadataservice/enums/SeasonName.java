package com.nesstiff.reference.metadataservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum SeasonName {
    SPRING(1, "spring"),
    SUMMER(2, "summer"),
    AUTUMN(3, "autumn"),
    WINTER(4, "winter");

    private final Integer id;
    private final String persianName;

    public static Optional<SeasonName> findById(int id) {
        for (SeasonName seasonName : SeasonName.values()) {
            if (seasonName.id == id) {
                return Optional.of(seasonName);
            }
        }
        return Optional.empty();
    }

}