package com.next.springframework.spring5recipeapp.controller;

import com.next.springframework.spring5recipeapp.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController controller;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks( this );

        controller = new IndexController( recipeService );
    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup( controller ).build();

        mockMvc.perform( get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name( "index" ) );
    }

    @Test
    public void getIndexPage() {

        String viewName = controller.getIndexPage( model );

        assertEquals( "index", viewName);

    }
}