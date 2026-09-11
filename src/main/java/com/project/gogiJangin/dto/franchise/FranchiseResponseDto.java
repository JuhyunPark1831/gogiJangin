package com.project.gogiJangin.dto.franchise;

import com.project.gogiJangin.entity.Franchise;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseResponseDto {

    private Long frId;
    private String frName;
    private String frContact;
    private String frHopeRegion;
    private String frSearchPath;
    private LocalDateTime regDt;
    private String frMemo;
    private String frStatus;


    @Builder
    public FranchiseResponseDto(Franchise fr) {
        this.frId = fr.getFrId();
        this.frName = fr.getFrName();
        this.frContact = fr.getFrContact();
        this.frHopeRegion = fr.getFrHopeRegion();
        this.frSearchPath = fr.getFrSearchPath();
        this.regDt = fr.getRegDt();
        this.frMemo = fr.getFrMemo();
        this.frStatus = fr.getFrStatus();
    }
}
