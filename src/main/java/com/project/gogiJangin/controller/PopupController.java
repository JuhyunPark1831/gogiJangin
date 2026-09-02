package com.project.gogiJangin.controller;

import com.project.gogiJangin.common.response.CustomResponseEntity;
import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.franchise.FranchiseRequestDto;
import com.project.gogiJangin.dto.franchise.FranchiseResponseDto;
import com.project.gogiJangin.dto.popup.PopupRequestDto;
import com.project.gogiJangin.dto.popup.PopupResponseDto;
import com.project.gogiJangin.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PopupController {

    private final PopupService popupService;

    @GetMapping("/admin/popup/list.do")
    public String popupList() {
        return "/admin/popup/popup_list";
    }

    @GetMapping("/admin/popup/view.do")
    public String popupView() {
        return "/admin/popup/popup_view";
    }

    @GetMapping("/admin/popup/write.do")
    public String popupWrite() {
        return "/admin/popup/popup_write";
    }

    @PostMapping("/admin/popup/api/postPopup.do")
    @ResponseBody
    public CustomResponseEntity<Long> postPopup(@ModelAttribute PopupRequestDto requestDto) {
        return CustomResponseEntity.success("팝업이 등록되었습니다.", popupService.addPopup(requestDto));
    }

    @GetMapping("/admin/popup/api/viewPopupDetail.do/{puId}")
    @ResponseBody
    public CustomResponseEntity<PopupResponseDto> viewPopupDetail(@PathVariable Long puId) {
        return CustomResponseEntity.success(null, popupService.getPopupDetail(puId));
    }

    @GetMapping("/admin/popup/api/viewPopupList.do")
    @ResponseBody
    public CustomResponseEntity<PageResponse<PopupResponseDto>> viewPopupList(Pageable pageable) {
        return CustomResponseEntity.success(null, popupService.getPopupList(pageable));
    }

    @PutMapping("/admin/popup/api/editPopup.do")
    @ResponseBody
    public CustomResponseEntity<Long> editPopup(@ModelAttribute PopupRequestDto requestDto) {
        return CustomResponseEntity.success("팝업이 변경되었습니다", popupService.updatePopup(requestDto));
    }

    @DeleteMapping("/admin/popup/api/deletePopup.do/{puId}")
    @ResponseBody
    public CustomResponseEntity<Object> deleteFranchise(@PathVariable Long puId) {
        popupService.deletePopup(puId);
        return CustomResponseEntity.success("팝업이 삭제되었습니다", null);
    }
}
