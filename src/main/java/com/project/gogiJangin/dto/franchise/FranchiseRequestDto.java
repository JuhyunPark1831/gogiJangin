package com.project.gogiJangin.dto.franchise;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseRequestDto {

    private String frName;
    private String frContact;
    private Long frRgId;
}
