package dev.project.ecuadorcomparte.model.repository;

import dev.project.ecuadorcomparte.model.constant.Category;
import dev.project.ecuadorcomparte.model.entity.Testimony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TestimonyRepository extends JpaRepository<Testimony, Long> {

    List<Testimony> findByPublishedTrueOrderByCreatedAtDesc();

    List<Testimony> findByCategoryAndPublishedTrueOrderByCreatedAtDesc(Category category);

    List<Testimony> findAllByOrderByCreatedAtDesc();

    long countByPublishedFalse();
}