package dev.project.ecuadorcomparte.controller;

import dev.project.ecuadorcomparte.model.constant.ContactStatus;
import dev.project.ecuadorcomparte.model.dto.ContactRequestDTO;
import dev.project.ecuadorcomparte.model.service.ContactRequestService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact1")
public class ContactController {

    private final ContactRequestService contactService;

    public ContactController(ContactRequestService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String showForm(Model model) {

        model.addAttribute(
                "contactForm",
                new ContactRequestDTO(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                )
        );

        model.addAttribute(
                "activePage",
                "contact"
        );

        return "contact/contact1";
    }

    @PostMapping
    public String submitForm(
            @ModelAttribute("contactForm")
            ContactRequestDTO dto,
            Model model
    ) {

        contactService.save(dto);

        model.addAttribute(
                "successMessage",
                "¡Gracias! Tu mensaje fue enviado correctamente."
        );

        model.addAttribute(
                "activePage",
                "contact"
        );

        return "contact/contact1";
    }

    @PostMapping("/{id}/delete")
    public String deleteContact(@PathVariable Long id) {

        contactService.deleteById(id);

        return "redirect:/dashboard/contacts";
    }

    @GetMapping("/login")
    public String login() {

        return "contact/login";
    }

    @PostMapping("/{id}/status")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam("status") ContactStatus status
    ) {

        contactService.updateStatus(id, status);

        return "redirect:/dashboard/contacts";
    }
}