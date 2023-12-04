package com.pantrypalbackend.pantrypalbackend.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import com.pantrypalbackend.pantrypalbackend.domain.User;
import com.pantrypalbackend.pantrypalbackend.domain.UserCreatedRecipe;
import com.pantrypalbackend.pantrypalbackend.dto.CreateUserMadeRecipeRequest;
import com.pantrypalbackend.pantrypalbackend.dto.UpdateUserMadeRecipeRequest;
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
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));

        user.getFavoriteRecipes().add(recipe);
        System.out.println("user.getFavoriteRecipes() " + user.getFavoriteRecipes());
        userRepository.save(user);
    }

    public void removeRecipeFromFavorites(Long userId, String recipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));

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
        User user = userRepository.findById(createUserMadeRecipeRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Recipe recipe = buildUserCreatedRecipe(createUserMadeRecipeRequest);
        Recipe newlyAddedRecipe = recipeRepository.save(recipe);

        UserCreatedRecipe userCreatedRecipe = UserCreatedRecipe.builder()
                .recipe(newlyAddedRecipe)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        userCreatedRecipeRepository.save(userCreatedRecipe);

        return newlyAddedRecipe;
    }

    public List<Recipe> getUserCreatedRecipes(Long userId) {
        List<UserCreatedRecipe> userRecipes = userCreatedRecipeRepository.findByUserId(userId);
        System.out.println("\nuserRecipes: " + userRecipes);
        return userRecipes.stream()
                .map(UserCreatedRecipe::getRecipe)
                .collect(Collectors.toList());
    }

    public Recipe updateUserMadeRecipe(UpdateUserMadeRecipeRequest updateUserMadeRecipeRequest) {
        Recipe originalRecipe = recipeRepository.findById(updateUserMadeRecipeRequest.getRecipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found with id " + updateUserMadeRecipeRequest.getRecipeId()));
        System.out.println("\noriginalRecipe" + originalRecipe);

        Recipe updatedRecipe = buildUpdatedRecipe(updateUserMadeRecipeRequest, originalRecipe);
        System.out.println("\nupdatedRecipe" + updatedRecipe);

        return recipeRepository.save(updatedRecipe);
    }

    public void deleteUserMadeRecipe(String recipeId) {
        // Optional: Check if the recipe exists in the user_created_recipes table
        UserCreatedRecipe userCreatedRecipe = userCreatedRecipeRepository.findByRecipeId(recipeId)
                .orElseThrow(() -> new RuntimeException("User created recipe not found with id " + recipeId));

        // Delete the user_created_recipe record if it exists
        userCreatedRecipeRepository.deleteById(userCreatedRecipe.getId());

        // Delete the recipe
        recipeRepository.deleteById(recipeId);
    }


    private Recipe buildUserCreatedRecipe(CreateUserMadeRecipeRequest payload) {
        return Recipe.builder()
                .id("user_" + payload.getUserId() + "_" + UUID.randomUUID())
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

    private Recipe buildUpdatedRecipe(UpdateUserMadeRecipeRequest request, Recipe recipe) {
        // Update recipe fields
        if (request.getName() != null) {
            recipe.setName(request.getName());
        }
        if (request.getSource() != null) {
            recipe.setSource(request.getSource());
        }
        if (request.getPreptime() != null) {
            recipe.setPrepTime(request.getPreptime());
        }
        if (request.getWaittime() != null) {
            recipe.setWaitTime(request.getWaittime());
        }
        if (request.getCooktime() != null) {
            recipe.setCookTime(request.getCooktime());
        }
        if (request.getServings() != null) {
            recipe.setServings(request.getServings());
        }
        if (request.getComments() != null) {
            recipe.setComments(request.getComments());
        }
        if (request.getCalories() != null) {
            recipe.setCalories(request.getCalories());
        }
        if (request.getFat() != null) {
            recipe.setFat(request.getFat());
        }
        if (request.getCarbs() != null) {
            recipe.setCarbs(request.getCarbs());
        }
        if (request.getFiber() != null) {
            recipe.setFiber(request.getFiber());
        }
        if (request.getSugar() != null) {
            recipe.setSugar(request.getSugar());
        }
        if (request.getProtein() != null) {
            recipe.setProtein(request.getProtein());
        }
        if (request.getInstructions() != null) {
            recipe.setInstructions(request.getInstructions());
        }
        if (request.getIngredients() != null) {
            recipe.setIngredients(request.getIngredients());
        }
        if (request.getTags() != null) {
            recipe.setTags(request.getTags());
        }
        if (request.getSatfat() != null) {
            recipe.setSatFat(request.getSatfat());
        }

        return recipe;
    }

}
