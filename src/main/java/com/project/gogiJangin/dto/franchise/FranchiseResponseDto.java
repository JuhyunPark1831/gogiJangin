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
    private Long frRgId;
    private String frRgName;
    private LocalDateTime frRegDt;
    private LocalDateTime frModDt;

    @Builder
    public FranchiseResponseDto(Franchise fr) {
        this.frId = fr.getFrId();
        this.frName = fr.getFrName();
        this.frContact = fr.getFrContact();
        this.frRgId = fr.getFrRegion().getRgId();
        this.frRgName = fr.getFrRegion().getRgName();
        this.frRegDt = fr.getRegDt();
        this.frModDt = fr.getModDt();
    }
}
