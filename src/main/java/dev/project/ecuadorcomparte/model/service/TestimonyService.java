package dev.project.ecuadorcomparte.model.service;

import dev.project.ecuadorcomparte.model.constant.Category;
import dev.project.ecuadorcomparte.model.dto.TestimonyDTO;
import dev.project.ecuadorcomparte.model.entity.Testimony;
import dev.project.ecuadorcomparte.model.repository.TestimonyRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestimonyService {

    private final TestimonyRepository repository;

    public TestimonyService(TestimonyRepository repository) {
        this.repository = repository;
    }

    public TestimonyDTO save(TestimonyDTO dto) {
        Testimony entity = dto.toEntity();
        return toDTO(repository.save(entity));
    }

    public TestimonyDTO update(Long id, TestimonyDTO dto) {
        Testimony existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonio no encontrado: " + id));

        existing.setTitle(dto.title());
        existing.setSummary(dto.summary());
        existing.setContent(dto.content());
        existing.setAuthor(dto.author());
        existing.setLocation(dto.location());
        existing.setCategory(dto.category());
        existing.setImageUrl(dto.imageUrl());
        existing.setPublished(dto.published());

        return toDTO(repository.save(existing));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<TestimonyDTO> findPublished() {
        return repository.findByPublishedTrueOrderByCreatedAtDesc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TestimonyDTO> findByCategory(Category category) {
        return repository.findByCategoryAndPublishedTrueOrderByCreatedAtDesc(category)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TestimonyDTO> findAll() {
        return repository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TestimonyDTO findById(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Testimonio no encontrado: " + id));
    }

    public long countDrafts() {
        return repository.countByPublishedFalse();
    }

    private TestimonyDTO toDTO(Testimony entity) {
        return new TestimonyDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getSummary(),
                entity.getContent(),
                entity.getAuthor(),
                entity.getLocation(),
                entity.getCategory(),
                entity.getImageUrl(),
                entity.isPublished()
        );
    }
}