package org.example.smartkitchen.controllers;

import org.example.smartkitchen.dto.FavoriteRecipeDTO;
import org.example.smartkitchen.service.interfaces.FavoriteRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes/favorite_recipes")
public class FavoriteRecipeController {

    private final FavoriteRecipeService favoriteRecipeService;

    @Autowired
    public FavoriteRecipeController(FavoriteRecipeService favoriteRecipeService) {
        this.favoriteRecipeService = favoriteRecipeService;
    }

    @GetMapping("/favorite_recipes")
    public ResponseEntity<List<FavoriteRecipeDTO>> getFavoriteRecipes() {

        List<FavoriteRecipeDTO> favoriteRecipes = favoriteRecipeService.getFavoriteRecipesByEmail();
        return ResponseEntity.ok(favoriteRecipes);
    }

    @PostMapping("/favorite_recipes/{recipeId}")
    public ResponseEntity<Void> addRecipeToFavorites(@PathVariable Long recipeId) {
        favoriteRecipeService.addRecipeToFavorites(recipeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/favorite_recipes/{recipeId}")
    public ResponseEntity<Void> removeRecipeFromFavorites(@PathVariable Long recipeId) {
        favoriteRecipeService.removeRecipeFromFavorites(recipeId);
        return ResponseEntity.ok().build();
    }
}

