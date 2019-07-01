package com.next.springframework.spring5recipeapp.service;

import com.next.springframework.spring5recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
