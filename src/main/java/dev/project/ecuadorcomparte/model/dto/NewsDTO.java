package dev.project.ecuadorcomparte.model.dto;

import dev.project.ecuadorcomparte.model.constant.Category;
import dev.project.ecuadorcomparte.model.entity.News;
import java.time.LocalDateTime; // O el tipo de fecha que uses

public record NewsDTO(
        Long id,
        String title,
        String summary,
        String content,
        Category category,
        String imageUrl,
        boolean published,
        int readingTimeMin,
        LocalDateTime createdAt
) {

    public News toEntity() {
        News news = new News();
        news.setId(id);
        news.setTitle(this.title);
        news.setSummary(this.summary);
        news.setContent(this.content);
        news.setCategory(this.category);
        news.setImageUrl(this.imageUrl);
        news.setPublished(this.published);
        news.setReadingTimeMin(this.readingTimeMin);
        news.setCreatedAt(this.createdAt);

        return news;
    }
}