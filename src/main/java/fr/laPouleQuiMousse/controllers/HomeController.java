package fr.laPouleQuiMousse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.unbescape.html.HtmlEscape;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "home/index";
    }
}
