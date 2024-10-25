package org.example.smartkitchen.dto.Ingredient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.smartkitchen.domain.entity.Ingredient.AbstractIngredientEntity;
import org.example.smartkitchen.domain.entity.recipe.RecipeEntity;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseIngredientDTO {

    private Long id;

    private Long abstractIngredientId;

    private Long recipeId;
}
