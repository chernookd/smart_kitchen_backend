package org.example.smartkitchen.service.interfaces;

import org.example.smartkitchen.dto.RecipeDTO;

import java.util.List;

public interface RecipeService {
    RecipeDTO getRecipeById(Long id);

    List<RecipeDTO> getAllRecipes();
}
