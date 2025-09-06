package com.nesstiff.reference.metadataservice.entity;

import com.nesstiff.reference.metadataservice.enums.RecordStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Column(nullable = false)
    private String title;

    @Nationalized
    @Column(nullable = false)
    private String faTitle;

    private String description;

    @Nationalized
    private String faDescription;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(nullable = false)
    private UUID createdBy;

    @LastModifiedBy
    private UUID lastModifiedBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

}
