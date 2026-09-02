package com.project.gogiJangin.dto.popup;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PopupRequestDto {

    private Long puId;
    private String puTitle;
    private MultipartFile puImage;
    private String puImagePath;
    private LocalDateTime puStartDt;
    private LocalDateTime puEndDt;

    @Builder
    public PopupRequestDto(Long puId,
                           String puTitle,
                           MultipartFile puImage,
                           String puImagePath,
                           LocalDateTime puStartDt,
                           LocalDateTime puEndDt) {
        this.puId = puId;
        this.puTitle = puTitle;
        this.puImage = puImage;
        this.puImagePath = puImagePath;
        this.puStartDt = puStartDt;
        this.puEndDt = puEndDt;
    }
}
