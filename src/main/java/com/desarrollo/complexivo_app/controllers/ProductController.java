package com.desarrollo.complexivo_app.controllers;

import com.desarrollo.complexivo_app.models.Product;
import com.desarrollo.complexivo_app.repository.CategoryRepository;
import com.desarrollo.complexivo_app.services.CategoryService;
import com.desarrollo.complexivo_app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/add")
    public String getFormProduct(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", new Product());
        return "product/add-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product){
        productService.saveProduct(product);
        return "redirect:/";  //Redireccionar home
    }


}
