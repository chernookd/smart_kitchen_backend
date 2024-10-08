package org.example.smartkitchen.controllers;

import org.example.smartkitchen.dto.RecipeHistoryDTO;
import org.example.smartkitchen.service.interfaces.RecipeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recipes/recipe_history")
public class RecipeHistoryController {

    private final RecipeHistoryService recipeHistoryService;

    @Autowired
    public RecipeHistoryController(RecipeHistoryService recipeHistoryService) {
        this.recipeHistoryService = recipeHistoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RecipeHistoryDTO>> getRecipeHistory() {
        List<RecipeHistoryDTO> recipeHistory = recipeHistoryService.getRecipeHistoryByEmail();
        return ResponseEntity.ok(recipeHistory);
    }
}
