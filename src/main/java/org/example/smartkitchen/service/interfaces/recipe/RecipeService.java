package org.example.smartkitchen.service.interfaces.recipe;

import org.example.smartkitchen.domain.entity.recipe.RecipeEntity;
import org.example.smartkitchen.dto.recipe.RecipeDTO;

import java.util.List;

public interface RecipeService {
    RecipeDTO getRecipeById(Long id);

    List<RecipeDTO> getAllRecipes();

    RecipeEntity createRecipe(RecipeEntity recipe);

    RecipeEntity updateRecipe(Long id, RecipeEntity recipeDetails);
}
