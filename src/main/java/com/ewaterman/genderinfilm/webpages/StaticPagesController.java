package com.ewaterman.genderinfilm.webpages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ewaterman.genderinfilm.reports.ReportService;

@Controller
public class StaticPagesController {

    @GetMapping("/about")
    String homepage(Model model) {
        return "about";
    }
}
