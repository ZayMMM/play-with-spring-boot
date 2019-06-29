package com.next.springframework.spring5recipeapp.repository;

import com.next.springframework.spring5recipeapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
