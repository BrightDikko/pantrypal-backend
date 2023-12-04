package com.pantrypalbackend.pantrypalbackend.repository;

import com.pantrypalbackend.pantrypalbackend.domain.UserCreatedRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCreatedRecipeRepository extends JpaRepository<UserCreatedRecipe, Long> {
    Optional<UserCreatedRecipe> findByRecipeId(String recipeId);

    List<UserCreatedRecipe> findByUserId(Long userId);

}
