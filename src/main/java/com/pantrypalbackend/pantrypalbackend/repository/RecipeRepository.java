package com.pantrypalbackend.pantrypalbackend.repository;

import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
