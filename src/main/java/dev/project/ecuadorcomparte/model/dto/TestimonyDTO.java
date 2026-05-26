package dev.project.ecuadorcomparte.model.dto;

import dev.project.ecuadorcomparte.model.constant.Category;
import dev.project.ecuadorcomparte.model.entity.Testimony;

public record TestimonyDTO(
        Long id,
        String title,
        String summary,
        String content,
        String author,
        String location,
        Category category,
        String imageUrl,
        boolean published
) {

    public Testimony toEntity() {
        Testimony testimony = new Testimony();
        testimony.setTitle(this.title);
        testimony.setSummary(this.summary);
        testimony.setContent(this.content);
        testimony.setAuthor(this.author);
        testimony.setLocation(this.location);
        testimony.setCategory(this.category);
        testimony.setImageUrl(this.imageUrl);
        testimony.setPublished(this.published);
        return testimony;
    }
}