package com.project.gogiJangin.controller;

import com.project.gogiJangin.common.response.CustomResponseEntity;
import com.project.gogiJangin.dto.popup.PopupRequestDto;
import com.project.gogiJangin.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class PopupController {

    private final PopupService popupService;

    @GetMapping("/admin/popup/list.do")
    public String popupList(@PageableDefault(size = 10) Pageable pageable,
                            Model model) {
        model.addAttribute("popupList", popupService.getPopupList(null, pageable));
        model.addAttribute("now", LocalDateTime.now());
        return "/admin/popup/popup_list";
    }

    @PostMapping("/admin/popup/api/list.do")
    public String popupListSearch(@RequestBody PopupRequestDto requestDto,
                                  @PageableDefault(size = 10) Pageable pageable,
                                  Model model) {
        model.addAttribute("popupList", popupService.getPopupList(requestDto, pageable));
        model.addAttribute("now", LocalDateTime.now());
        return "/admin/popup/popup_list :: #popup-list";
    }

    @GetMapping("/admin/popup/view.do/{puId}")
    public String popupView(Model model,
                            @PathVariable Long puId) {
        model.addAttribute("popupDetail", popupService.getPopupDetail(puId));
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
