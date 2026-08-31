package com.project.gogiJangin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    @GetMapping("/admin/review/list.do")
    public String reviewList() {
        return "/admin/review/review_list";
    }

    @GetMapping("/admin/review/view.do")
    public String reviewView() {
        return "/admin/review/review_view";
    }

    @GetMapping("/admin/review/write.do")
    public String reviewWrite() {
        return "/admin/review/review_write";
    }
}
