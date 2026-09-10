package com.project.gogiJangin.service;

import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.popup.PopupResponseDto;
import com.project.gogiJangin.dto.review.ReviewRequestDto;
import com.project.gogiJangin.dto.review.ReviewResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    public Long addReview(ReviewRequestDto requestDto);
    ReviewResponseDto getReviewDetail(Long rvId);
    PageResponse<ReviewResponseDto> getReviewList(ReviewRequestDto requestDto, Pageable pageable);
    void deleteReview(Long rvId);

    List<ReviewResponseDto> getActiveReviewList();
}
