package com.nesstiff.reference.metadataservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RecordStatus {
    CREATED,
    UPDATED,
    DISABLED,
    DELETED,
}
