package com.example.warehouse.controller;

import com.example.warehouse.entity.Product;
import com.example.warehouse.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public String getProductPage(
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "sort", defaultValue = "id_asc") String sort,
            Model model) {
        List<Product> products = productService.findAll(search,sort);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("edit", false);
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "addProduct";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{productId}")
    public String getProductEditPage(@PathVariable Long productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("edit", true);
        return "addProduct";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return "redirect:/products";
    }

}
