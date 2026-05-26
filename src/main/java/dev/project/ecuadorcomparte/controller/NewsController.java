package dev.project.ecuadorcomparte.controller;


import dev.project.ecuadorcomparte.model.constant.Category;
import dev.project.ecuadorcomparte.model.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public String list(@RequestParam(required = false) String category, Model model) {
        if (category != null && !category.isBlank()) {
            try {
                Category cat = Category.valueOf(category.toUpperCase());
                model.addAttribute("newsList", newsService.findByCategory(cat));
                model.addAttribute("selectedCategory", category);
            } catch (IllegalArgumentException e) {
                model.addAttribute("newsList", newsService.findPublished());
            }
        } else {
            model.addAttribute("newsList", newsService.findPublished());
            model.addAttribute("selectedCategory", "TODOS");
        }
        model.addAttribute("categories", Category.values());
        model.addAttribute("activePage", "news");

        return "news/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsService.findById(id));
        model.addAttribute("activePage", "news");
        return "news/detail";
    }
}
