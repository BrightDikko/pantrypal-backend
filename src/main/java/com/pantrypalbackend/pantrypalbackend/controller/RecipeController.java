package com.pantrypalbackend.pantrypalbackend.controller;

import com.pantrypalbackend.pantrypalbackend.constants.PathConstants;
import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import com.pantrypalbackend.pantrypalbackend.service.Impl.RecipeDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.RECIPE)
public class RecipeController {

    private final RecipeDataServiceImpl recipeDataService;

    @GetMapping
    public ResponseEntity<Recipe> getRecipeByName(@RequestParam String name) {
        System.out.println("\nRequest to get recipe by name received. \nRequest: " + name);

        Recipe recipe = recipeDataService.getRecipeByName(name);

        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
