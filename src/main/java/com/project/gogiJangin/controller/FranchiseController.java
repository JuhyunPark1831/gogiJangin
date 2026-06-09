package com.project.gogiJangin.controller;

import com.project.gogiJangin.common.exception.CustomException;
import com.project.gogiJangin.common.exception.ErrorCode;
import com.project.gogiJangin.common.response.CustomResponseEntity;
import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.franchise.FranchiseRequestDto;
import com.project.gogiJangin.dto.franchise.FranchiseResponseDto;
import com.project.gogiJangin.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/franchise")
public class FranchiseController {

    private final FranchiseService franchiseService;

    @PostMapping("/api/inquireFranchise.do")
    @ResponseBody
    public CustomResponseEntity<Long> inquireFranchise(@RequestBody FranchiseRequestDto requestDto) {
        return CustomResponseEntity.success("가맹문의가 등록되었습니다.", franchiseService.addFranchise(requestDto));
    }

    @GetMapping("/api/viewFranchiseDetail.do/{frId}")
    @ResponseBody
    public CustomResponseEntity<FranchiseResponseDto> viewFranchiseDetail(@PathVariable Long frId) {
        return CustomResponseEntity.success(null, franchiseService.getFranchiseDetail(frId));
    }

    @GetMapping("/api/viewFranchiseList.do")
    @ResponseBody
    public CustomResponseEntity<PageResponse<FranchiseResponseDto>> viewFranchiseList(Pageable pageable) {
        return CustomResponseEntity.success(null, franchiseService.getFranchiseList(pageable));
    }
}
