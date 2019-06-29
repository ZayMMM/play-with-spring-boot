package com.next.springframework.spring5recipeapp.controller;

import com.next.springframework.spring5recipeapp.model.Category;
import com.next.springframework.spring5recipeapp.model.UnitOfMeasure;
import com.next.springframework.spring5recipeapp.repository.CategoryRepository;
import com.next.springframework.spring5recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepo;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    IndexController(CategoryRepository catRepo, UnitOfMeasureRepository uomRepo){
        this.categoryRepo = catRepo;
        this.unitOfMeasureRepository = uomRepo;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){

        Optional<Category> categoryOptional = categoryRepo.findByDescription( "American" );
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription( "Teaspoon" );

        System.out.println("Category Id is : " + categoryOptional.get().getId());
        System.out.println("UnitOfMeasure Id is : " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
