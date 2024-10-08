package org.example.smartkitchen.service.impl;

import org.example.smartkitchen.dto.RecipeHistoryDTO;
import org.example.smartkitchen.service.interfaces.RecipeHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecipeHistoryServiceImpl implements RecipeHistoryService {
    @Override
    public List<RecipeHistoryDTO> getRecipeHistoryByEmail() {
        return List.of();
    }
}
