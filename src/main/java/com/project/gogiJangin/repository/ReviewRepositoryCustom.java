package com.project.gogiJangin.repository;

import com.project.gogiJangin.dto.review.ReviewRequestDto;
import com.project.gogiJangin.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<Review> search(
            ReviewRequestDto requestDto,
            Pageable pageable
    );
}
