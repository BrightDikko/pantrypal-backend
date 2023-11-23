package com.pantrypalbackend.pantrypalbackend.controller;

import com.pantrypalbackend.pantrypalbackend.constants.PathConstants;
import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import com.pantrypalbackend.pantrypalbackend.service.Impl.RecipeDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.RECIPE)
public class RecipeController {

    private final RecipeDataServiceImpl recipeDataService;

    @GetMapping
    public ResponseEntity<Recipe> getRecipeByName(@RequestParam String name) {
        System.out.println("\nRequest to get recipe by name received. \nName: " + name);

        Recipe recipe = recipeDataService.getRecipeByName(name);

        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/searchByIngredients")
    public ResponseEntity<List<Recipe>> getRecipesByIngredients(@RequestBody List<String> ingredients) {
        System.out.println("Request to get recipes by ingredients received. \nIngredients: " + ingredients);

        List<Recipe> matchingRecipes = recipeDataService.getRecipesByIngredients(ingredients);
        return ResponseEntity.ok(matchingRecipes);
    }
}
