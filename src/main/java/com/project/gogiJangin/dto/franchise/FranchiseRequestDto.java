package com.project.gogiJangin.dto.franchise;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseRequestDto {

    private Long frId;
    private String frName;
    private String frContact;
    private String frHopeRegion;
    private String frSearchPath;
    private String frMemo;
    private String frStatus;
    private LocalDateTime frStartDt;
    private LocalDateTime frEndDt;
}
