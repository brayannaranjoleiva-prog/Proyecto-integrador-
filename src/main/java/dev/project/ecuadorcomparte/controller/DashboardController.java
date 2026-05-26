package dev.project.ecuadorcomparte.controller;

import dev.project.ecuadorcomparte.model.constant.ContactStatus;
import dev.project.ecuadorcomparte.model.dto.NewsDTO;
import dev.project.ecuadorcomparte.model.dto.TestimonyDTO;
import dev.project.ecuadorcomparte.model.service.ContactRequestService;
import dev.project.ecuadorcomparte.model.service.NewsService;
import dev.project.ecuadorcomparte.model.service.TestimonyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ContactRequestService contactService;
    private final TestimonyService testimonyService;
    private final NewsService newsService;

    public DashboardController(
            ContactRequestService contactService,
            TestimonyService testimonyService,
            NewsService newsService
    ) {
        this.contactService = contactService;
        this.testimonyService = testimonyService;
        this.newsService = newsService;
    }

    // =========================
    // DASHBOARD
    // =========================

    @GetMapping
    public String dashboard(Model model) {

        model.addAttribute(
                "contacts",
                contactService.findAll()
        );

        model.addAttribute(
                "pendingCount",
                contactService.countPending()
        );

        model.addAttribute(
                "testimonials",
                testimonyService.findAll()
        );

        model.addAttribute(
                "activePage",
                "dashboard"
        );

        return "dashboard/index";
    }

    // =========================
    // CONTACTS
    // =========================

    @GetMapping("/contacts")
    public String contacts(
            @RequestParam(required = false) String status,
            Model model
    ) {

        if (status != null && !status.isBlank()) {

            ContactStatus contactStatus =
                    ContactStatus.valueOf(status);

            model.addAttribute(
                    "contacts",
                    contactService.findByStatus(contactStatus)
            );

        } else {

            model.addAttribute(
                    "contacts",
                    contactService.findAll()
            );
        }

        model.addAttribute(
                "statuses",
                ContactStatus.values()
        );

        model.addAttribute(
                "pendingCount",
                contactService.countPending()
        );

        model.addAttribute(
                "activePage",
                "contacts"
        );

        return "dashboard/contacts";
    }

    @GetMapping("/contacts/{id}")
    public String contactDetail(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
                "contact",
                contactService.findById(id)
        );

        model.addAttribute(
                "activePage",
                "contacts"
        );

        return "dashboard/contact-detail";
    }

    // UPDATE STATUS

    @PostMapping("/contacts/{id}/status")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam("status") ContactStatus status
    ) {

        contactService.updateStatus(id, status);

        return "redirect:/dashboard/contacts";
    }

    // DELETE CONTACT

    @PostMapping("/contacts/{id}/delete")
    public String deleteContact(
            @PathVariable Long id
    ) {

        contactService.deleteById(id);

        return "redirect:/dashboard/contacts";
    }

    // =========================
    // TESTIMONIES
    // =========================

    @GetMapping("/testimony")
    public String testimonials(Model model) {

        model.addAttribute(
                "testimonials",
                testimonyService.findAll()
        );

        model.addAttribute(
                "activePage",
                "testimony"
        );

        return "dashboard/testimony";
    }

    @GetMapping("/testimony/new")
    public String newTestimonyForm(Model model) {

        model.addAttribute(
                "testimonyForm",
                new TestimonyDTO(
                        null,
                        "",
                        "",
                        "",
                        "",
                        "",
                        null,
                        "",
                        false
                )
        );

        model.addAttribute(
                "activePage",
                "testimony"
        );

        return "dashboard/testimony-form";
    }

    @PostMapping("/testimony/new")
    public String saveTestimony(
            @ModelAttribute("testimonyForm")
            TestimonyDTO dto
    ) {

        testimonyService.save(dto);

        return "redirect:/dashboard/testimony";
    }

    @GetMapping("/testimony/{id}/edit")
    public String editTestimonyForm(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
                "testimonyForm",
                testimonyService.findById(id)
        );

        model.addAttribute(
                "activePage",
                "testimony"
        );

        return "dashboard/testimony-form";
    }

    @PostMapping("/testimony/{id}/edit")
    public String updateTestimony(
            @PathVariable Long id,
            @ModelAttribute("testimonyForm")
            TestimonyDTO dto
    ) {

        testimonyService.update(id, dto);

        return "redirect:/dashboard/testimony";
    }

    @PostMapping("/testimony/{id}/delete")
    public String deleteTestimony(
            @PathVariable Long id
    ) {

        testimonyService.delete(id);

        return "redirect:/dashboard/testimony";
    }

    // =========================
    // NEWS
    // =========================

    @GetMapping("/news")
    public String news(Model model) {

        model.addAttribute(
                "newsList",
                newsService.findAll()
        );

        model.addAttribute(
                "activePage",
                "news"
        );

        return "dashboard/news";
    }

    @GetMapping("/news/new")
    public String newNewsForm(Model model) {

        model.addAttribute(
                "newsForm",
                new NewsDTO(
                        null,
                        "",
                        "",
                        "",
                        null,
                        "",
                        false,
                        0,
                        java.time.LocalDateTime.now()
                )
        );

        model.addAttribute(
                "activePage",
                "news"
        );

        return "dashboard/news-form";
    }

    @PostMapping("/news/new")
    public String saveNews(
            @ModelAttribute("newsForm")
            NewsDTO dto
    ) {

        newsService.save(dto);

        return "redirect:/dashboard/news";
    }

    @GetMapping("/news/{id}/edit")
    public String editNewsForm(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
                "newsForm",
                newsService.findById(id)
        );

        model.addAttribute(
                "activePage",
                "news"
        );

        return "dashboard/news-form";
    }

    @PostMapping("/news/{id}/edit")
    public String updateNews(
            @PathVariable Long id,
            @ModelAttribute("newsForm")
            NewsDTO dto
    ) {

        newsService.update(id, dto);

        return "redirect:/dashboard/news";
    }

    @PostMapping("/news/{id}/delete")
    public String deleteNews(
            @PathVariable Long id
    ) {

        newsService.delete(id);

        return "redirect:/dashboard/news";
    }
}