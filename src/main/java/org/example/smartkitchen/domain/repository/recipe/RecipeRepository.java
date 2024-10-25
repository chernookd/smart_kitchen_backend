package org.example.smartkitchen.domain.repository.recipe;

import org.example.smartkitchen.domain.entity.recipe.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
}
