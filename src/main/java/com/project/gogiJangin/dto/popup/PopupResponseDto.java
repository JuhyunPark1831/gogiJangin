package com.project.gogiJangin.dto.popup;

import com.project.gogiJangin.entity.Popup;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PopupResponseDto {

    private Long puId;
    private String puTitle;
    private Long puAfId;
    private LocalDateTime puStartDt;
    private LocalDateTime puEndDt;
    private String status;
    private LocalDateTime regDt;

    @Builder
    public PopupResponseDto(Popup popup,
                            Long puAfId) {

        this.puId = popup.getPuId();
        this.puTitle = popup.getPuTitle();
        this.puAfId = puAfId;
        this.puStartDt = popup.getPuStartDt();
        this.puEndDt = popup.getPuEndDt();
        this.regDt = popup.getRegDt();

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(popup.getPuStartDt())) {
            this.status = "01";
        } else if (now.isAfter(popup.getPuEndDt())) {
            this.status = "03";
        } else {
            this.status = "02";
        }
    }
}
