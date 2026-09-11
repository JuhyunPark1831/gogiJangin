package com.project.gogiJangin.entity;

import com.project.gogiJangin.dto.franchise.FranchiseRequestDto;
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

    @Column(name = "FR_HOPE_REGION")
    private String frHopeRegion;

    @Column(name = "FR_SEARCH_PATH")
    private String frSearchPath;

    @Column(name = "FR_MEMO")
    private String frMemo;

    @Column(name = "FR_STATUS")
    private String frStatus;

    @Builder
    public Franchise(String frName,
                     String frContact,
                     String frHopeRegion,
                     String frSearchPath,
                     String frMemo,
                     String frStatus) {
        this.frName = frName;
        this.frContact = frContact;
        this.frHopeRegion = frHopeRegion;
        this.frSearchPath = frSearchPath;
        this.frMemo = frMemo;
        this.frStatus = frStatus;
    }

    public void updateFrMemoAndStatus(FranchiseRequestDto requestDto) {
        this.frMemo = requestDto.getFrMemo();
        this.frStatus = requestDto.getFrStatus();
    }
}