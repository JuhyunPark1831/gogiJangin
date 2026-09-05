package com.project.gogiJangin.controller;

import com.project.gogiJangin.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PopupService popupService;

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("popupList", popupService.getActivePopupList());
        return "/customer/index";
    }
}
