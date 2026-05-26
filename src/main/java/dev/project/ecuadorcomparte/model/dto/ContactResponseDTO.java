package dev.project.ecuadorcomparte.model.dto;

import dev.project.ecuadorcomparte.model.constant.ContactStatus;

import java.time.LocalDateTime;

public record ContactResponseDTO(

        Long id,
        String fullName,
        String email,
        String phone,
        String message,
        String subject,
        ContactStatus status,
        String city,
        LocalDateTime createdAt

) {


}