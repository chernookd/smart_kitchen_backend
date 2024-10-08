package org.example.smartkitchen.controllers;

import org.example.smartkitchen.dto.IngredientDTO;
import org.example.smartkitchen.service.interfaces.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class IngredientController {

    private IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @GetMapping("/base-ingredients/{id}")
    public ResponseEntity<IngredientDTO> getBaseIngredientById(@PathVariable Long id) {
        IngredientDTO ingredient = ingredientService.getBaseIngredientById(id);
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/user-ingredients/{id}")
    public ResponseEntity<IngredientDTO> getUserIngredientById(@PathVariable Long id) {
        IngredientDTO ingredient = ingredientService.getUserIngredientById(id);
        return ResponseEntity.ok(ingredient);
    }
}
