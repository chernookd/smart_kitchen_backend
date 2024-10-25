package org.example.smartkitchen.dto.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIngredientDTO {
    private Long id;
    private int quantity;
    private String userDescription;
    private Long recipeId;
    private Long abstractIngredientId;
}
