package dev.project.ecuadorcomparte.controller;

import dev.project.ecuadorcomparte.model.constant.Category;
import dev.project.ecuadorcomparte.model.service.TestimonyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/testimony")
public class TestimonyController {

    private final TestimonyService testimonyService;

    public TestimonyController(TestimonyService testimonyService) {
        this.testimonyService = testimonyService;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) String category,
            Model model
    ) {
        if (category != null && !category.isBlank()) {
            try {
                Category cat = Category.valueOf(category.toUpperCase());
                model.addAttribute(
                        "testimonials",
                        testimonyService.findByCategory(cat)
                );
                model.addAttribute(
                        "selectedCategory",
                        category
                );
            } catch (IllegalArgumentException e) {
                model.addAttribute(
                        "testimonials",
                        testimonyService.findPublished()
                );
            }
        } else {
            model.addAttribute(
                    "testimonials",
                    testimonyService.findPublished()
            );
            model.addAttribute(
                    "selectedCategory",
                    "TODOS"
            );
        }

        model.addAttribute(
                "categories",
                Category.values()
        );
        model.addAttribute(
                "activePage",
                "testimony"
        );

        return "testimony/list";
    }

    @GetMapping("/{id}")
    public String detail(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute(
                "testimony",
                testimonyService.findById(id)
        );
        model.addAttribute(
                "activePage",
                "testimony"
        );

        return "testimony/detail";
    }
}