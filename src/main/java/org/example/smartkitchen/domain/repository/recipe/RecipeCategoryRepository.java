package org.example.smartkitchen.domain.repository.recipe;

import org.example.smartkitchen.domain.entity.recipe.RecipeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategoryEntity, Long> {
}
