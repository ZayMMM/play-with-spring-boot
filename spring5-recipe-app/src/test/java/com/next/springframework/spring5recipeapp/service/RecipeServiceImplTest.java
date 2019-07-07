package com.next.springframework.spring5recipeapp.service;

import com.next.springframework.spring5recipeapp.model.Recipe;
import com.next.springframework.spring5recipeapp.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks( this );

        recipeService = new RecipeServiceImpl( recipeRepository );
    }

    @Test
    public void getRecipes() {

       /* Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet<>(  );
        recipeData.add( recipe );

        //when( recipeService.getRecipes() ).thenReturn( recipeData );

        Set<Recipe> recipeSet = recipeService.getRecipes();

        //assertEquals( recipeSet.size(), 1 );*/
    }
}