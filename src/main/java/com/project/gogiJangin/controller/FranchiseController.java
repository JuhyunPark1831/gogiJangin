package com.project.gogiJangin.controller;

import com.project.gogiJangin.common.response.CustomResponseEntity;
import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.franchise.FranchiseRequestDto;
import com.project.gogiJangin.dto.franchise.FranchiseResponseDto;
import com.project.gogiJangin.dto.popup.PopupRequestDto;
import com.project.gogiJangin.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    @GetMapping("/admin/franchise/list.do")
    public String franchiseList(@PageableDefault(size = 10) Pageable pageable,
                                Model model) {
        model.addAttribute("franchiseList", franchiseService.getFranchiseList(null, pageable));
        model.addAttribute("now", LocalDateTime.now());
        return "/admin/inquiry/inquiry_list";
    }

    @PostMapping("/admin/franchise/api/list.do")
    public String franchiseListSearch(@RequestBody FranchiseRequestDto requestDto,
                                  @PageableDefault(size = 10) Pageable pageable,
                                  Model model) {
        model.addAttribute("franchiseList", franchiseService.getFranchiseList(requestDto, pageable));
        model.addAttribute("now", LocalDateTime.now());
        return "/admin/inquiry/inquiry_list :: #franchise-list";
    }

    @GetMapping("/admin/franchise/view.do/{frId}")
    public String franchiseView(Model model,
                                @PathVariable Long frId) {
        model.addAttribute("franchiseDetail", franchiseService.getFranchiseDetail(frId));
        return "/admin/inquiry/inquiry_view";
    }

    @PostMapping("/franchise/api/inquireFranchise.do")
    @ResponseBody
    public CustomResponseEntity<Long> inquireFranchise(@RequestBody FranchiseRequestDto requestDto) {
        return CustomResponseEntity.success("가맹문의가 등록되었습니다.", franchiseService.addFranchise(requestDto));
    }

    @PutMapping("/admin/franchise/api/editFranchise.do")
    @ResponseBody
    public CustomResponseEntity<Long> editFranchise(@RequestBody FranchiseRequestDto requestDto) {
        return CustomResponseEntity.success("가맹문의가 변경되었습니다", franchiseService.updateFranchise(requestDto));
    }

    @DeleteMapping("/admin/franchise/api/deleteFranchise.do/{frId}")
    @ResponseBody
    public CustomResponseEntity<Object> deleteFranchise(@PathVariable Long frId) {
        franchiseService.deleteFranchise(frId);
        return CustomResponseEntity.success("가맹문의가 삭제되었습니다", null);
    }
}
