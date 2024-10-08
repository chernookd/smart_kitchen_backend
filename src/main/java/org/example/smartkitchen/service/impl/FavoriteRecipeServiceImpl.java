package org.example.smartkitchen.service.impl;

import org.example.smartkitchen.dto.FavoriteRecipeDTO;
import org.example.smartkitchen.service.interfaces.FavoriteRecipeService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavoriteRecipeServiceImpl implements FavoriteRecipeService {
    @Override
    public List<FavoriteRecipeDTO> getFavoriteRecipesByEmail() {
        return List.of();
    }

    @Override
    public void addRecipeToFavorites(Long recipeId) {

    }

    @Override
    public void removeRecipeFromFavorites(Long recipeId) {

    }
}
