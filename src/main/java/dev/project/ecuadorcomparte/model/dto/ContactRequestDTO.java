package dev.project.ecuadorcomparte.model.dto;

import dev.project.ecuadorcomparte.model.entity.ContactRequest;

public record ContactRequestDTO(

        String fullName,
        String email,
        String phone,
        String subject,
        String message,
        String city

) {
    public ContactRequestDTO() {
        this(null, null, null, null, null, null);
    }

    public ContactRequest toEntity() {

        ContactRequest request = new ContactRequest();

        request.setFullName(this.fullName);
        request.setEmail(this.email);
        request.setPhone(this.phone);
        request.setSubject(this.subject);
        request.setSubject(this.subject);
        request.setMessage(this.message);
        request.setCity(this.city);

        return request;
    }



}