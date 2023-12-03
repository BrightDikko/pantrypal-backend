package com.pantrypalbackend.pantrypalbackend.controller;

import com.pantrypalbackend.pantrypalbackend.constants.PathConstants;
import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import com.pantrypalbackend.pantrypalbackend.service.Impl.RecipeDataServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PathConstants.RECIPE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RecipeController {

    private final RecipeDataServiceImpl recipeDataService;

    @GetMapping
    @Operation(summary = "Get Recipe by Name",
            description = "Retrieve a recipe by its name.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recipe found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class))),
                    @ApiResponse(responseCode = "404", description = "Recipe not found")
            })
    public ResponseEntity<Recipe> getRecipeByName(@RequestParam String name) {
        Recipe recipe = recipeDataService.getRecipeByName(name);

        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/searchByIngredients")
    @Operation(summary = "Search Recipes by Ingredients",
            description = "Retrieve recipes based on a list of ingredients.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "List of ingredients to search for",
                    content = @Content(schema = @Schema(implementation = List.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recipes found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class)))
            })
    public ResponseEntity<List<Recipe>> getRecipesByIngredients(@RequestBody List<String> ingredients) {
        List<Recipe> matchingRecipes = recipeDataService.getRecipesByIngredients(ingredients);
        return ResponseEntity.ok(matchingRecipes);
    }
}
