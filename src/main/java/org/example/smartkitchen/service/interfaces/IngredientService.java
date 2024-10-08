package org.example.smartkitchen.service.interfaces;

import org.example.smartkitchen.dto.IngredientDTO;

public interface IngredientService {
    IngredientDTO getBaseIngredientById(Long id);

    IngredientDTO getUserIngredientById(Long id);
}
