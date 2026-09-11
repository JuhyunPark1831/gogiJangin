package com.project.gogiJangin.dto.review;

import com.project.gogiJangin.entity.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewResponseDto {

    private Long rvId;
    private String rvTitle;
    private Long rvAfId;
    private String rvStatus;
    private LocalDateTime regDt;
    private String rvPlatform;
    private int rvOrder;

    @Builder
    public ReviewResponseDto(Review review,
                             Long rvAfId) {

        this.rvId = review.getRvId();
        this.rvTitle = review.getRvTitle();
        this.rvAfId = rvAfId;
        this.rvStatus = review.getRvStatus();
        this.regDt = review.getRegDt();
        this.rvPlatform = review.getRvPlatform();
        this.rvOrder = review.getRvOrder();
    }
}
