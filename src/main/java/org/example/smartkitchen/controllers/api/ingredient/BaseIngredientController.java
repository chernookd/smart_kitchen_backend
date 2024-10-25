package org.example.smartkitchen.controllers.api.ingredient;

import lombok.extern.slf4j.Slf4j;
import org.example.smartkitchen.domain.entity.Ingredient.BaseIngredientEntity;
import org.example.smartkitchen.dto.Ingredient.BaseIngredientDTO;
import org.example.smartkitchen.service.impl.indredient.BaseIngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/base_ingredients")
@Slf4j
public class BaseIngredientController {
    private final BaseIngredientServiceImpl baseIngredientService;

    @Autowired
    public BaseIngredientController(BaseIngredientServiceImpl baseIngredientService) {
        this.baseIngredientService = baseIngredientService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BaseIngredientDTO> getBaseIngredientById(@PathVariable Long id) {
        BaseIngredientDTO baseIngredientDTO = baseIngredientService.getBaseIngredientById(id);
        return ResponseEntity.ok(baseIngredientDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<BaseIngredientDTO> createBaseIngredient(@RequestBody BaseIngredientDTO baseIngredientDTO) {
        log.info("createBaseIngredient {}", baseIngredientDTO);

        BaseIngredientDTO createdBaseIngredient = baseIngredientService.createBaseIngredient(baseIngredientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBaseIngredient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseIngredientDTO> updateBaseIngredient(@PathVariable Long id, @RequestBody BaseIngredientDTO baseIngredientDTO) {
        BaseIngredientDTO updatedBaseIngredient = baseIngredientService.updateBaseIngredient(id, baseIngredientDTO);
        return ResponseEntity.ok(updatedBaseIngredient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBaseIngredient(@PathVariable Long id) {
        baseIngredientService.deleteBaseIngredient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public List<BaseIngredientDTO> getAllBaseIngredients() {
        return baseIngredientService.getAllBaseIngredients();
    }
}
