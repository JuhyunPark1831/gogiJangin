package com.project.gogiJangin.service.impl;

import com.project.gogiJangin.common.exception.CustomException;
import com.project.gogiJangin.common.exception.ErrorCode;
import com.project.gogiJangin.common.module.FileHandler;
import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.review.ReviewRequestDto;
import com.project.gogiJangin.dto.review.ReviewResponseDto;
import com.project.gogiJangin.entity.Popup;
import com.project.gogiJangin.entity.Review;
import com.project.gogiJangin.repository.AttachFileRepository;
import com.project.gogiJangin.repository.ReviewRepository;
import com.project.gogiJangin.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    @Value("${file.upload-path}")
    private String path;

    private final ReviewRepository reviewRepository;
    private final AttachFileRepository attachFileRepository;
    private final FileHandler fileHandler;

    // 리뷰 생성
    @Override
    @Transactional
    public Long addReview(ReviewRequestDto requestDto) {

        List<MultipartFile> list = List.of(requestDto.getRvImage());

        return reviewRepository.save(Review.builder()
                .rvTitle(requestDto.getRvTitle())
                .rvAfGroupId(fileHandler.saveFiles(list, path + File.separator + "review"))
                .rvStatus(requestDto.getRvStatus())
                .rvPlatform(requestDto.getRvPlatform())
                .rvOrder(requestDto.getRvOrder())
                .build()).getRvId();
    }

    // 리뷰 단건 조회
    @Override
    public ReviewResponseDto getReviewDetail(Long rvId) {

        Review review = reviewRepository.findById(rvId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_REVIEW));

        Long rvAfId = attachFileRepository.findAfIdByAfGroupId(review.getRvAfGroupId());

        return ReviewResponseDto.builder()
                .review(review)
                .rvAfId(rvAfId)
                .build();
    }

    // 리뷰 목록 조회
    @Override
    public PageResponse<ReviewResponseDto> getReviewList(ReviewRequestDto requestDto, Pageable pageable) {

        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "regDt")
        );

        Page<Review> reviewPage;

        if (requestDto != null) {
            reviewPage = reviewRepository.search(requestDto, sortedPageable);
        } else {
            reviewPage = reviewRepository.findAllByDelYn("N", sortedPageable);
        }

        List<ReviewResponseDto> content = reviewPage.getContent()
                .stream()
                .map(review -> ReviewResponseDto.builder()
                        .review(review)
                        .rvAfId(attachFileRepository.findAfIdByAfGroupId(review.getRvAfGroupId()))
                        .build()) // or mapper 사용
                .toList();

        return PageResponse.<ReviewResponseDto>builder()
                .content(content)
                .page(reviewPage.getNumber())
                .size(reviewPage.getSize())
                .totalElements(reviewPage.getTotalElements())
                .totalPages(reviewPage.getTotalPages())
                .first(reviewPage.isFirst())
                .last(reviewPage.isLast())
                .build();
    }

    // 팝업 삭제
    @Override
    @Transactional
    public void deleteReview(Long rvId) {

        Review review = reviewRepository.findById(rvId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_REVIEW));

        fileHandler.deleteAllFileByAfGroupId(review.getRvAfGroupId());
        review.softDelete();
    }
}
