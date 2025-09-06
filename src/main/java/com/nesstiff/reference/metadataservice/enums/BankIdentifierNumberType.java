package com.nesstiff.reference.metadataservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum BankIdentifierNumberType {

    IBAN(0),
    CARD(1);

    private final Integer id;

    public static Optional<BankIdentifierNumberType> findById(int id) {
        for (BankIdentifierNumberType bankIdentifierNumberType : BankIdentifierNumberType.values()) {
            if (bankIdentifierNumberType.id == id) {
                return Optional.of(bankIdentifierNumberType);
            }
        }
        return Optional.empty();
    }
}
