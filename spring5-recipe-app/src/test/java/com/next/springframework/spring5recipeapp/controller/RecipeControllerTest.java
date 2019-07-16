package com.next.springframework.spring5recipeapp.controller;

import com.next.springframework.spring5recipeapp.model.Recipe;
import com.next.springframework.spring5recipeapp.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeController recipeController;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController( recipeService );
    }


    @Test
    public void testGetRecipe() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId( 1L );
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup( recipeController).build();

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect( view().name( "recipe/show" ) );
    }
}