package org.example.smartkitchen.service.interfaces.indredient;

import org.example.smartkitchen.dto.Ingredient.IngredientDTO;

public interface IngredientService {
    IngredientDTO getBaseIngredientById(Long id);

    IngredientDTO getUserIngredientById(Long id);
}
