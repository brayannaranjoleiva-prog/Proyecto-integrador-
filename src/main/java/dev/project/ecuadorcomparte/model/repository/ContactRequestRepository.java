package dev.project.ecuadorcomparte.model.repository;

import dev.project.ecuadorcomparte.model.constant.ContactStatus;
import dev.project.ecuadorcomparte.model.entity.ContactRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRequestRepository
        extends JpaRepository<ContactRequest, Long> {

    List<ContactRequest> findByStatusOrderByCreatedAtDesc(
            ContactStatus status
    );

    List<ContactRequest> findAllByOrderByCreatedAtDesc();

    long countByStatus(ContactStatus status);

    List<ContactRequest> findByStatus(ContactStatus status);
}