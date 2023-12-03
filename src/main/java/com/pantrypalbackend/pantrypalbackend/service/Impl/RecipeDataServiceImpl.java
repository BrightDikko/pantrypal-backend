package com.pantrypalbackend.pantrypalbackend.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import com.pantrypalbackend.pantrypalbackend.domain.User;
import com.pantrypalbackend.pantrypalbackend.domain.UserCreatedRecipe;
import com.pantrypalbackend.pantrypalbackend.dto.CreateUserMadeRecipeRequest;
import com.pantrypalbackend.pantrypalbackend.repository.RecipeRepository;
import com.pantrypalbackend.pantrypalbackend.repository.UserCreatedRecipeRepository;
import com.pantrypalbackend.pantrypalbackend.repository.UserRepository;
import com.pantrypalbackend.pantrypalbackend.service.RecipeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeDataServiceImpl implements RecipeDataService {

    private final ObjectMapper objectMapper;

    private final UserRepository userRepository;
    private final UserCreatedRecipeRepository userCreatedRecipeRepository;
    private final RecipeRepository recipeRepository;

    public void loadAndSaveRecipeData(String path) throws IOException {
        Map<String, Recipe> recipes = objectMapper.readValue(new File(path), new TypeReference<Map<String, Recipe>>() {
        });

        recipeRepository.saveAll(recipes.values());
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAllRecipes();
    }

    public Recipe getRecipeByName(String name) {
        Optional<Recipe> recipeOptional = recipeRepository.retrieveRecipeByName(name);
        return recipeOptional.orElse(null);
    }

    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        List<Recipe> allRecipes = getAllRecipes();
        List<Recipe> matchingRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            // Extract individual words from each ingredient string
            Set<String> recipeIngredientWords = recipe.getIngredients().stream()
                    .flatMap(ingredient -> Arrays.stream(ingredient.split("\\s+")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "")) // Remove non-alphabetic characters
                    .filter(word -> !word.isEmpty() && Character.isLetter(word.charAt(0))) // Filter out empty strings and strings not starting with a letter
                    .collect(Collectors.toSet());

            // Check if all provided ingredients are contained within the recipe's ingredients
            if (recipeIngredientWords.containsAll(ingredients)) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;

    }

    public void addRecipeToFavorites(Long userId, String recipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId)).orElseThrow(() -> new RuntimeException("Recipe not found"));

        user.getFavoriteRecipes().add(recipe);
        System.out.println("user.getFavoriteRecipes() " + user.getFavoriteRecipes());
        userRepository.save(user);
    }

    public void removeRecipeFromFavorites(Long userId, String recipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId)).orElseThrow(() -> new RuntimeException("Recipe not found"));

        user.getFavoriteRecipes().remove(recipe);
        userRepository.save(user);
        userRepository.flush();
    }

    public List<Recipe> getUserFavoriteRecipes(Long userId) {
        return userRepository.findById(userId)
                .map(User::getFavoriteRecipes)
                .map(ArrayList::new)
                .orElse(new ArrayList<>());
    }

    public Recipe createUserMadeRecipe(CreateUserMadeRecipeRequest createUserMadeRecipeRequest) {

        System.out.println("\n recipe: " + createUserMadeRecipeRequest);

        User user = userRepository.findById(createUserMadeRecipeRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("\n user: " + user);

        Recipe recipe = buildUserCreatedRecipe(createUserMadeRecipeRequest);

        Recipe newlyAddedRecipe = recipeRepository.save(recipe);
        System.out.println("\n newlyAddedRecipe: " + newlyAddedRecipe);

        UserCreatedRecipe userCreatedRecipe = UserCreatedRecipe.builder()
                .recipe(newlyAddedRecipe)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        System.out.println("\n userCreatedRecipe: " + userCreatedRecipe);
        userCreatedRecipeRepository.save(userCreatedRecipe);

        return newlyAddedRecipe;
    }

    private Recipe buildUserCreatedRecipe(CreateUserMadeRecipeRequest payload) {
        return Recipe.builder()
                .id("user_" + payload.getUserId() + "_" + UUID.randomUUID().toString())
                .name(payload.getName())
                .source(payload.getSource())
                .prepTime(payload.getPreptime())
                .waitTime(payload.getWaittime())
                .cookTime(payload.getCooktime())
                .servings(payload.getServings())
                .comments(payload.getComments())
                .calories(payload.getCalories())
                .fat(payload.getFat())
                .carbs(payload.getCarbs())
                .fiber(payload.getFiber())
                .sugar(payload.getSugar())
                .protein(payload.getProtein())
                .instructions(payload.getInstructions())
                .ingredients(payload.getIngredients())
                .tags(payload.getTags())
                .satFat(payload.getSatfat())
                .build();
    }


}
