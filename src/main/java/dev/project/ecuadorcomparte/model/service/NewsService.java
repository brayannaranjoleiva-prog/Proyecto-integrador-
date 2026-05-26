package dev.project.ecuadorcomparte.model.service;

import dev.project.ecuadorcomparte.model.constant.Category;
import dev.project.ecuadorcomparte.model.dto.NewsDTO;
import dev.project.ecuadorcomparte.model.entity.News;
import dev.project.ecuadorcomparte.model.repository.NewsRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepository repository;


    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    public NewsDTO save(NewsDTO dto) {

        News entity = dto.toEntity();

        return toDTO(repository.save(entity));
    }

    public NewsDTO update(Long id, NewsDTO dto) {

        News existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Noticia no encontrada: " + id));

        existing.setTitle(dto.title());
        existing.setSummary(dto.summary());
        existing.setContent(dto.content());
        existing.setCategory(dto.category());
        existing.setImageUrl(dto.imageUrl());
        existing.setPublished(dto.published());
        existing.setReadingTimeMin(dto.readingTimeMin());

        return toDTO(repository.save(existing));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<NewsDTO> findPublished() {

        return repository.findByPublishedTrueOrderByCreatedAtDesc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<NewsDTO> findByCategory(Category category) {

        return repository.findByCategoryAndPublishedTrueOrderByCreatedAtDesc(category)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<NewsDTO> findAll() {

        return repository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public NewsDTO findById(Long id) {

        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() ->
                        new RuntimeException("Noticia no encontrada: " + id));
    }



    private NewsDTO toDTO(News entity) {

        return new NewsDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getSummary(),
                entity.getContent(),
                entity.getCategory(),
                entity.getImageUrl(),
                entity.isPublished(),
                entity.getReadingTimeMin(),
                entity.getCreatedAt()


        );
    }
}