package com.pantrypalbackend.pantrypalbackend.controller;

import com.pantrypalbackend.pantrypalbackend.constants.PathConstants;
import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import com.pantrypalbackend.pantrypalbackend.dto.CreateUserMadeRecipeRequest;
import com.pantrypalbackend.pantrypalbackend.dto.FavoriteRecipeRequest;
import com.pantrypalbackend.pantrypalbackend.dto.FavoriteRecipeResponse;
import com.pantrypalbackend.pantrypalbackend.dto.UpdateUserMadeRecipeRequest;
import com.pantrypalbackend.pantrypalbackend.service.Impl.RecipeDataServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/favorites")
    @Operation(summary = "Add Recipe to User's Favorites",
            description = "Add a recipe to a user's list of favorite recipes.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User ID and Recipe ID to add to favorites",
                    content = @Content(schema = @Schema(implementation = FavoriteRecipeRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recipe added to favorites"),
                    @ApiResponse(responseCode = "404", description = "User or Recipe not found")
            })
    public ResponseEntity<FavoriteRecipeResponse> addFavoriteRecipe(@RequestBody FavoriteRecipeRequest favoriteRecipeRequest) {
        recipeDataService.addRecipeToFavorites(favoriteRecipeRequest.getUserId(), favoriteRecipeRequest.getRecipeId());
        return ResponseEntity.ok(FavoriteRecipeResponse.builder()
                .message("Successfully added recipe to user's favorites list!")
                .build());
    }

    @DeleteMapping("/favorites")
    @Operation(summary = "Remove Recipe from User's Favorites",
            description = "Remove a recipe from a user's list of favorite recipes.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User ID and Recipe ID to remove from favorites",
                    content = @Content(schema = @Schema(implementation = FavoriteRecipeRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recipe removed from favorites"),
                    @ApiResponse(responseCode = "404", description = "User or Recipe not found")
            })
    public ResponseEntity<FavoriteRecipeResponse> removeFavoriteRecipe(@RequestBody FavoriteRecipeRequest favoriteRecipeRequest) {
        recipeDataService.removeRecipeFromFavorites(favoriteRecipeRequest.getUserId(), favoriteRecipeRequest.getRecipeId());
        return ResponseEntity.ok(FavoriteRecipeResponse.builder()
                .message("Successfully removed recipe from user's favorites list!")
                .build());
    }

    @GetMapping("/{userId}/favorites")
    @Operation(summary = "Get User's Favorite Recipes",
            description = "Retrieve all favorite recipes of a specific user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Favorite recipes found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    public ResponseEntity<List<Recipe>> getUserFavoriteRecipes(@PathVariable Long userId) {
        List<Recipe> favoriteRecipes = recipeDataService.getUserFavoriteRecipes(userId);

        if (!favoriteRecipes.isEmpty()) {
            return ResponseEntity.ok(favoriteRecipes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addUserMadeRecipe")
    @Operation(summary = "Add Recipes Created by a User",
            description = "Add new user-created recipe.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Recipe to be added, and the user who created it",
                    content = @Content(schema = @Schema(implementation = CreateUserMadeRecipeRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recipe added successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CreateUserMadeRecipeRequest.class)))
            })
    public ResponseEntity<Recipe> adduserMadeRecipe(@RequestBody CreateUserMadeRecipeRequest request) {
        System.out.println("\n CreateUserMadeRecipeRequest: " + request);

        Recipe createdRecipe = recipeDataService.createUserMadeRecipe(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecipe);
    }

    @PutMapping("/updateUserMadeRecipe")
    public ResponseEntity<Recipe> updateUserMadeRecipe(@RequestBody UpdateUserMadeRecipeRequest request) {
        Recipe updatedRecipe = recipeDataService.updateUserMadeRecipe(request);
        return ResponseEntity.ok(updatedRecipe);
    }

}
