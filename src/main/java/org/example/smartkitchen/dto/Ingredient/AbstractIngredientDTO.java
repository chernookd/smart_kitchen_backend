package org.example.smartkitchen.dto.Ingredient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.smartkitchen.domain.entity.Ingredient.IngredientCategoryEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractIngredientDTO {

    private Long id;

    private String name;

    private String description;


    private Long ingredientCategoryId;
}
