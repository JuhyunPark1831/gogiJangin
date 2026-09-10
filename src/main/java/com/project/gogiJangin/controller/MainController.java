package com.project.gogiJangin.controller;

import com.project.gogiJangin.service.PopupService;
import com.project.gogiJangin.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PopupService popupService;
    private final ReviewService reviewService;

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("popupList", popupService.getActivePopupList());
        model.addAttribute("reviewList", reviewService.getActiveReviewList());
        return "/customer/index";
    }
}
