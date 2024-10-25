package org.example.smartkitchen.service.impl.recipe;

import org.example.smartkitchen.dto.FavoriteRecipeDTO;
import org.example.smartkitchen.service.interfaces.recipe.FavoriteRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavouriteRecipeServiceImpl implements FavoriteRecipeService {

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
