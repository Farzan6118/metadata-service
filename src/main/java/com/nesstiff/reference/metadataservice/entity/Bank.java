package com.nesstiff.reference.metadataservice.entity;

import com.nesstiff.reference.metadataservice.enums.BankIdentifierNumberType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
public class Bank extends BaseEntity<Integer> {

    @Nationalized
    @Column(nullable = false)
    private String nativeName;

    @Column(nullable = false)
    private String identifierNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BankIdentifierNumberType bankIdentifierNumberType;

    private String logoUrl;
}
