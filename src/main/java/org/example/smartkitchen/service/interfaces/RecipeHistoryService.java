package org.example.smartkitchen.service.interfaces;

import org.example.smartkitchen.dto.RecipeHistoryDTO;

import java.util.List;

public interface RecipeHistoryService {
    List<RecipeHistoryDTO> getRecipeHistoryByEmail();
}
