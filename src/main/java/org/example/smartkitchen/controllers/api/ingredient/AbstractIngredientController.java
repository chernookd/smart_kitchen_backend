package org.example.smartkitchen.controllers.api.ingredient;

import lombok.extern.slf4j.Slf4j;
import org.example.smartkitchen.dto.Ingredient.AbstractIngredientDTO;
import org.example.smartkitchen.service.impl.indredient.AbstractIngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abstract_ingredients")
@Slf4j
public class AbstractIngredientController {
    private final AbstractIngredientServiceImpl abstractIngredientService;

    @Autowired
    public AbstractIngredientController(AbstractIngredientServiceImpl abstractIngredientService) {
        this.abstractIngredientService = abstractIngredientService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AbstractIngredientDTO> getAbstractIngredientById(@PathVariable Long id) {
        AbstractIngredientDTO abstractIngredientDTO = abstractIngredientService.getAbstractIngredientById(id);
        return ResponseEntity.ok(abstractIngredientDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<AbstractIngredientDTO> createAbstractIngredient(@RequestBody AbstractIngredientDTO abstractIngredientDTO) {
        log.info("createAbstractIngredient {}", abstractIngredientDTO);
        AbstractIngredientDTO createdAbstractIngredient = abstractIngredientService.createAbstractIngredient(abstractIngredientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAbstractIngredient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AbstractIngredientDTO> updateAbstractIngredient(@PathVariable Long id, @RequestBody AbstractIngredientDTO abstractIngredientDTO) {
        AbstractIngredientDTO updatedAbstractIngredient = abstractIngredientService.updateAbstractIngredient(id, abstractIngredientDTO);
        return ResponseEntity.ok(updatedAbstractIngredient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAbstractIngredient(@PathVariable Long id) {
        abstractIngredientService.deleteAbstractIngredient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<AbstractIngredientDTO> getAllAbstractIngredients() {
        return abstractIngredientService.getAllAbstractIngredients();
    }
}
