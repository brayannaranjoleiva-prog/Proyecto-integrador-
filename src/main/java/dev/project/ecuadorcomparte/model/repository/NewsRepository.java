package dev.project.ecuadorcomparte.model.repository;

import dev.project.ecuadorcomparte.model.constant.Category;
import dev.project.ecuadorcomparte.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByPublishedTrueOrderByCreatedAtDesc();

    List<News> findByCategoryAndPublishedTrueOrderByCreatedAtDesc(Category category);

    List<News> findAllByOrderByCreatedAtDesc();
    List<News> findTop3ByPublishedTrueOrderByCreatedAtDesc();
}