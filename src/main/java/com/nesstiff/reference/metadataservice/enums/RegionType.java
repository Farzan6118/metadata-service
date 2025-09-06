package com.nesstiff.reference.metadataservice.enums;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum RegionType {

    NONE(0, null, null),
    COUNTRY(1, "کشور", "country"),
    PROVINCE(2, "استان", "province"),
    CITY(3, "شهر", "city"),
    DISTRICT(4, "منطقه", "district");

    private final int id;
    private final String title;
    private final String enTitle;

    RegionType(int id, String title, String enTitle) {
        this.id = id;
        this.title = title;
        this.enTitle = enTitle;
    }


    public static Optional<RegionType> findById(int id) {
        for (RegionType regionType : RegionType.values()) {
            if (regionType.id == id) {
                return Optional.of(regionType);
            }
        }
        return Optional.empty();
    }
}
