package org.example.smartkitchen.service.interfaces.recipe;

import org.example.smartkitchen.dto.FavoriteRecipeDTO;

import java.util.List;

public interface FavoriteRecipeService {
    List<FavoriteRecipeDTO> getFavoriteRecipesByEmail();

    void addRecipeToFavorites(Long recipeId);

    void removeRecipeFromFavorites(Long recipeId);
}
