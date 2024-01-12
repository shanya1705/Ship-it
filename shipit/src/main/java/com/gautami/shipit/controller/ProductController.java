package com.gautami.shipit.controller;

import com.gautami.shipit.dto.ProductDto;
import com.gautami.shipit.model.Product;
import com.gautami.shipit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Product addProduct(@RequestBody ProductDto product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteProduct(@PathVariable Long id){
         productService.deleteProduct(id);
    }

    @PutMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Product updateProduct(@RequestBody ProductDto productDto,@PathVariable Long id){
        return productService.updateProduct(productDto,id);
    }






}
