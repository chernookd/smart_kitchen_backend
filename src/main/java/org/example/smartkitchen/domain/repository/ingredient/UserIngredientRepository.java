package org.example.smartkitchen.domain.repository.ingredient;

import org.example.smartkitchen.domain.entity.Ingredient.UserIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserIngredientRepository extends JpaRepository<UserIngredientEntity, Long> {
}
