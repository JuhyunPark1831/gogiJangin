package com.project.gogiJangin.controller;

import com.project.gogiJangin.common.response.CustomResponseEntity;
import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.franchise.FranchiseRequestDto;
import com.project.gogiJangin.dto.franchise.FranchiseResponseDto;
import com.project.gogiJangin.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    @GetMapping("/admin/franchise/list.do")
    public String franchiseList() {
        return "/admin/inquiry/inquiry_list";
    }

    @GetMapping("/admin/franchise/view.do")
    public String franchiseView() {
        return "/admin/inquiry/inquiry_view";
    }

    @GetMapping("/admin/franchise/write.do")
    public String franchiseWrite() {
        return "/admin/inquiry/inquiry_write";
    }

    @PostMapping("/franchise/api/inquireFranchise.do")
    @ResponseBody
    public CustomResponseEntity<Long> inquireFranchise(@RequestBody FranchiseRequestDto requestDto) {
        return CustomResponseEntity.success("가맹문의가 등록되었습니다.", franchiseService.addFranchise(requestDto));
    }

    @GetMapping("/franchise/api/viewFranchiseDetail.do/{frId}")
    @ResponseBody
    public CustomResponseEntity<FranchiseResponseDto> viewFranchiseDetail(@PathVariable Long frId) {
        return CustomResponseEntity.success(null, franchiseService.getFranchiseDetail(frId));
    }

    @PutMapping("/franchise/api/editFranchise.do")
    @ResponseBody
    public CustomResponseEntity<Long> editFranchise(@RequestBody FranchiseRequestDto requestDto) {
        return CustomResponseEntity.success("가맹문의가 변경되었습니다", franchiseService.updateFranchise(requestDto));
    }

    @DeleteMapping("/franchisee/api/deleteFranchise.do/{frId}")
    @ResponseBody
    public CustomResponseEntity<Object> deleteFranchise(@PathVariable Long frId) {
        franchiseService.deleteFranchise(frId);
        return CustomResponseEntity.success("가맹문의가 삭제되었습니다", null);
    }
}
