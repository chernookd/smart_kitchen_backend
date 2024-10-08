package org.example.smartkitchen.service.impl;

import org.example.smartkitchen.dto.RecipeDTO;
import org.example.smartkitchen.service.interfaces.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecipeServiceImpl implements RecipeService {

    public RecipeDTO getRecipeById(Long id) {
        return null;
    }

    public List<RecipeDTO> getAllRecipes() {
        return List.of();
    }
}
