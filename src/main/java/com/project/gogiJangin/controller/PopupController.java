package com.project.gogiJangin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PopupController {

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
}
