package com.nesstiff.reference.metadataservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum RegionType {

    COUNTRY(1, "country"),
    PROVINCE(2, "province"),
    CITY(3, "city"),
    DISTRICT(4, "district");

    private final int id;
    private final String title;

    public static Optional<RegionType> findById(int id) {
        for (RegionType regionType : RegionType.values()) {
            if (regionType.id == id) {
                return Optional.of(regionType);
            }
        }
        return Optional.empty();
    }
}
