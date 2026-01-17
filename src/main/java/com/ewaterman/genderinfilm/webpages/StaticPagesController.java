package com.ewaterman.genderinfilm.webpages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ewaterman.genderinfilm.common.base.PageController;

@Controller
public class StaticPagesController extends PageController {

    @GetMapping("/about")
    String about(Model model) {
        return "about";
    }
}
