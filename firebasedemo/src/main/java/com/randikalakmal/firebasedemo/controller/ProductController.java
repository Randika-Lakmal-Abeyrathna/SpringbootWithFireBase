package com.randikalakmal.firebasedemo.controller;

import com.randikalakmal.firebasedemo.entity.Product;
import com.randikalakmal.firebasedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    public ProductService productService;

    @PostMapping("/add")
    public String saveProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.saveProduct(product);
    }

    @GetMapping("/get/{name}")
    public Product getProduct(@PathVariable("name") String name) throws ExecutionException, InterruptedException {
        return productService.getProductDetails(name);
    }

    @PutMapping("/update")
    public String updateProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{name}")
    public String deleteProduct(@PathVariable("name") String name){
        return productService.deleteProduct(name);
    }
}
