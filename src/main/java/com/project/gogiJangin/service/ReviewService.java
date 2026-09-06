package com.project.gogiJangin.service;

import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.review.ReviewRequestDto;
import com.project.gogiJangin.dto.review.ReviewResponseDto;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    public Long addReview(ReviewRequestDto requestDto);
    ReviewResponseDto getReviewDetail(Long rvId);
    PageResponse<ReviewResponseDto> getReviewList(ReviewRequestDto requestDto, Pageable pageable);
    void deleteReview(Long rvId);
}
