package com.nesstiff.reference.metadataservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum ContinentName {
    AFRICA(1, "africa"),
    ANTARCTICA(1, "antarctica"),
    ASIA(1, "asia"),
    EUROPE(1, "europe"),
    NORTH_AMERICA(1, "north_america"),
    OCEANIA(1, "oceania"),
    SOUTH_AMERICA(1, "south_america");
    private final Integer id;
    private final String persianName;

    public static Optional<ContinentName> findById(int id) {
        for (ContinentName ContinentName : ContinentName.values()) {
            if (ContinentName.id == id) {
                return Optional.of(ContinentName);
            }
        }
        return Optional.empty();
    }
}