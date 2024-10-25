package org.example.smartkitchen.controllers.api.ingredient;

import org.example.smartkitchen.dto.Ingredient.UserIngredientDTO;
import org.example.smartkitchen.service.impl.indredient.UserIngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_ingredients")
public class UserIngredientController {
    private final UserIngredientServiceImpl userIngredientService;

    @Autowired
    public UserIngredientController(UserIngredientServiceImpl userIngredientService) {
        this.userIngredientService = userIngredientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserIngredientDTO> getUserIngredientById(@PathVariable Long id) {
        UserIngredientDTO userIngredientDTO = userIngredientService.getUserIngredientById(id);
        return ResponseEntity.ok(userIngredientDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<UserIngredientDTO> createUserIngredient(@RequestBody UserIngredientDTO userIngredientDTO) {
        UserIngredientDTO createdUserIngredient = userIngredientService.createUserIngredient(userIngredientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserIngredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserIngredientDTO> updateUserIngredient(@PathVariable Long id, @RequestBody UserIngredientDTO userIngredientDTO) {
        UserIngredientDTO updatedUserIngredient = userIngredientService.updateUserIngredient(id, userIngredientDTO);
        return ResponseEntity.ok(updatedUserIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserIngredient(@PathVariable Long id) {
        userIngredientService.deleteUserIngredient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<UserIngredientDTO> getAllUserIngredients() {
        return userIngredientService.getAllUserIngredients();
    }
}
