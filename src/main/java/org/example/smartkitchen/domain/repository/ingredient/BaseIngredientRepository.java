package org.example.smartkitchen.domain.repository.ingredient;

import org.example.smartkitchen.domain.entity.Ingredient.BaseIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BaseIngredientRepository extends JpaRepository<BaseIngredientEntity, Long> {
}
