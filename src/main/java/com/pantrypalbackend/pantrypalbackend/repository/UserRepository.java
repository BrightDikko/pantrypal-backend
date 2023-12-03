package com.pantrypalbackend.pantrypalbackend.repository;

import com.pantrypalbackend.pantrypalbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.favoriteRecipes WHERE u.id = :userId")
    Optional<User> findByIdWithFavoriteRecipes(Long userId);
}
