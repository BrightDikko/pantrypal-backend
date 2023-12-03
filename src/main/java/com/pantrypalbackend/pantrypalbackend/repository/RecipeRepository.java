package com.pantrypalbackend.pantrypalbackend.repository;

import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import com.pantrypalbackend.pantrypalbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT * FROM recipes WHERE name = :name", nativeQuery = true)
    Optional<Recipe> retrieveRecipeByName(String name);

    @Query(value = "SELECT * FROM recipes", nativeQuery = true)
    List<Recipe> findAllRecipes();

    @Query("SELECT r.users FROM Recipe r WHERE r.id = :recipeId")
    Optional<List<User>> findUsersByFavoriteRecipe(String recipeId);
}
