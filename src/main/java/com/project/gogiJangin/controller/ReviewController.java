package com.project.gogiJangin.controller;

import com.project.gogiJangin.common.response.CustomResponseEntity;
import com.project.gogiJangin.dto.popup.PopupRequestDto;
import com.project.gogiJangin.dto.review.ReviewRequestDto;
import com.project.gogiJangin.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/admin/review/list.do")
    public String reviewList(@PageableDefault(size = 10) Pageable pageable,
                             Model model) {
        model.addAttribute("reviewList", reviewService.getReviewList(null, pageable));
        model.addAttribute("now", LocalDateTime.now());
        return "/admin/review/review_list";
    }

    @PostMapping("/admin/review/api/list.do")
    public String reviewListSearch(@RequestBody ReviewRequestDto requestDto,
                                  @PageableDefault(size = 10) Pageable pageable,
                                  Model model) {
        model.addAttribute("reviewList", reviewService.getReviewList(requestDto, pageable));
        model.addAttribute("now", LocalDateTime.now());
        return "/admin/review/review_list :: #review-list";
    }

    @GetMapping("/admin/review/view.do/{rvId}")
    public String reviewView(Model model,
                             @PathVariable Long rvId) {
        model.addAttribute("reviewDetail", reviewService.getReviewDetail(rvId));
        return "/admin/review/review_view";
    }

    @GetMapping("/admin/review/write.do")
    public String reviewWrite() {
        return "/admin/review/review_write";
    }

    @PostMapping("/admin/review/api/addReview.do")
    @ResponseBody
    public CustomResponseEntity<Long> addReview(@ModelAttribute ReviewRequestDto reviewRequestDto) {
        return CustomResponseEntity.success("리뷰가 등록되었습니다.", reviewService.addReview(reviewRequestDto));
    }

    @DeleteMapping("/admin/review/api/deleteReview.do/{rvId}")
    @ResponseBody
    public CustomResponseEntity<Object> deleteReview(@PathVariable Long rvId) {
        reviewService.deleteReview(rvId);
        return CustomResponseEntity.success("리뷰가 삭제되었습니다", null);
    }
}
