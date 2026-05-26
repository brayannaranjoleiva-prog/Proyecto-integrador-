package dev.project.ecuadorcomparte.controller;

import dev.project.ecuadorcomparte.model.dto.ContactRequestDTO;
import dev.project.ecuadorcomparte.model.entity.News;
import dev.project.ecuadorcomparte.model.service.NewsService;
import dev.project.ecuadorcomparte.model.service.TestimonyService; // Mantener este servicio único
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NewsService newsService;
    private final TestimonyService testimonyService;

    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute("featuredStories", testimonyService.findPublished());

        model.addAttribute("latestNews", newsService.findPublished());

       model.addAttribute("testimony", testimonyService.findPublished());

        model.addAttribute("contactForm", new ContactRequestDTO());

       model.addAttribute("activePage", "home");

        return "home";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }


}