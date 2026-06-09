package com.project.gogiJangin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_FRANCHISE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Franchise extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FR_ID")
    private Long frId;

    @Column(name = "FR_NAME")
    private String frName;

    @Column(name = "FR_CONTACT")
    private String frContact;

    @ManyToOne
    @JoinColumn(name = "FR_RG_ID")
    private Region frRegion;

    @Builder
    public Franchise(String frName,
                     String frContact,
                     Region frRegion) {
        this.frName = frName;
        this.frContact = frContact;
        this.frRegion = frRegion;
    }
}