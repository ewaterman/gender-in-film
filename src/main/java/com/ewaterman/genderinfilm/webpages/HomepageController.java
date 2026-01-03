package com.ewaterman.genderinfilm.webpages;

import com.ewaterman.genderinfilm.common.base.PageController;
import com.ewaterman.genderinfilm.reports.ReportService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController extends PageController {

    private final ReportService reportService;

    public HomepageController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    String homepage(Model model) {
        model.addAttribute("primaryReport", reportService.generatePrimaryReport());
        return "homepage";
    }
}
