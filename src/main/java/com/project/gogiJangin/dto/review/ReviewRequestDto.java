package com.project.gogiJangin.dto.review;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ReviewRequestDto {

    private Long rvId;
    private String rvTitle;
    private MultipartFile rvImage;
    private String rvImagePath;
    private LocalDateTime rvStartDt;
    private LocalDateTime rvEndDt;
    private String rvStatus;
    private String rvPlatform;
    private int rvOrder;

    @Builder
    public ReviewRequestDto(Long rvId,
                            String rvTitle,
                            MultipartFile rvImage,
                            String rvImagePath,
                            LocalDateTime rvStartDt,
                            LocalDateTime rvEndDt,
                            String rvStatus,
                            String rvPlatform,
                            int rvOrder) {
        this.rvId = rvId;
        this.rvTitle = rvTitle;
        this.rvImage = rvImage;
        this.rvImagePath = rvImagePath;
        this.rvStartDt = rvStartDt;
        this.rvEndDt = rvEndDt;
        this.rvStatus = rvStatus;
        this.rvPlatform = rvPlatform;
        this.rvOrder = rvOrder;
    }
}
