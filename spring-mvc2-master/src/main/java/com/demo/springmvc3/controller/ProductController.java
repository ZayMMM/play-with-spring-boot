package com.demo.springmvc3.controller;

import com.demo.springmvc3.model.Product;
import com.demo.springmvc3.report.ProductReport;
import com.demo.springmvc3.service.CategoryService;
import com.demo.springmvc3.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_PDF;

@Controller
public class ProductController {

   @Autowired
    private ProductService productService;
   @Autowired
   private CategoryService categoryService;

    private int updateId;

    private static Logger logger=
            LoggerFactory.getLogger(ProductController.class);

   @GetMapping("/product")
   public String create(Model model){
     model.addAttribute("product",new Product());
     model.addAttribute("categories",categoryService.findAll());
     return "productForm";
   }
   @PostMapping("/product")
   public String process(@Valid Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes){

      if(result.hasErrors()){
        model.addAttribute("categories",categoryService.findAll());
        return "productForm";
      }

      productService.create(product);

      redirectAttributes.addFlashAttribute( "product1", true );

      return "redirect:/products";

   }
   @GetMapping("/products")
   public String showAllProducts(Model model){

     model.addAttribute("products",productService.findAll());

     model.addAttribute( "success1", model.containsAttribute( "product1" ) );

     model.addAttribute( "success2", model.containsAttribute( "update" ) );

     return "products";
   }

   @GetMapping("/products/{id}")
   public String removeProduct(@PathVariable("id") int id){
     productService.delete(id);

     return "redirect:/products";
   }

    @GetMapping("/products/update/{id}")
    public String update(@PathVariable("id") int id,Model model){
     this.updateId=id;
     model.addAttribute("categories",categoryService.findAll());
     model.addAttribute("product",productService.findById(id));
     return "updateForm";
    }

    @PostMapping("/products/update")
    public String processUpdate(Product product, RedirectAttributes redirectAttributes){
      productService.update(product,updateId);

      redirectAttributes.addFlashAttribute( "update", true );

      return "redirect:/products";
    }

    @GetMapping("/products/details/{id}")
    public String details(@PathVariable("id") int id,Model model){
       Product product=productService.findById(id);
       logger.info("id:"+ id);
       logger.info("Product:"+ product);
       model.addAttribute("product",product);
       return "productDetails";
    }

    @ResponseBody
    @GetMapping(value = "/api/products", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public List<Product> showAllProductsRest(){
        return productService.findAll();
    }

    @GetMapping(value = "/api/products/pdf")
    public ResponseEntity<InputStreamResource> generatePdf(){
        ByteArrayInputStream bis = ProductReport.productPdfView( productService.findAll() );
        HttpHeaders headers = new HttpHeaders(  );

        headers.add( "Content-Disposition",
                "inline:filename=productsReport.pdf");

        return ResponseEntity
                .ok(  )
                .contentType( APPLICATION_PDF )
                .body( new InputStreamResource( bis ) );
    }
}
