package dev.project.ecuadorcomparte.model.service;

import dev.project.ecuadorcomparte.model.constant.ContactStatus;
import dev.project.ecuadorcomparte.model.dto.ContactRequestDTO;
import dev.project.ecuadorcomparte.model.dto.ContactResponseDTO;
import dev.project.ecuadorcomparte.model.entity.ContactRequest;
import dev.project.ecuadorcomparte.model.repository.ContactRequestRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactRequestService {

    private final ContactRequestRepository repository;

    public ContactRequestService(ContactRequestRepository repository) {
        this.repository = repository;
    }

    public ContactResponseDTO save(ContactRequestDTO dto) {

        ContactRequest entity = dto.toEntity();

        return toDTO(repository.save(entity));
    }

    public List<ContactResponseDTO> findAll() {

        return repository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ContactResponseDTO> findByStatus(
            ContactStatus status
    ) {

        return repository.findByStatusOrderByCreatedAtDesc(status)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public long countPending() {
        return repository.countByStatus(ContactStatus.PENDING);
    }
    public ContactResponseDTO findById(Long id) {

        ContactRequest entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Contact not found"));

        return toDTO(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public void updateStatus(Long id, ContactStatus status) {

        ContactRequest contact = repository.findById(id)
                .orElseThrow();

        contact.setStatus(status);

        repository.save(contact);
    }

    private ContactResponseDTO toDTO(ContactRequest entity) {

        return new ContactResponseDTO(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getSubject(),
                entity.getMessage(),
                entity.getStatus(),
                entity.getCity(),
                entity.getCreatedAt()
        );
    }
}