package com.pantrypalbackend.pantrypalbackend.repository;

import com.pantrypalbackend.pantrypalbackend.domain.UserCreatedRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCreatedRecipeRepository extends JpaRepository<UserCreatedRecipe, Long> {

}
