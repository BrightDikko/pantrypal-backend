package com.pantrypalbackend.pantrypalbackend.repository;

import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
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
}
