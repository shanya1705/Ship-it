package com.gautami.shipit.controller;

import com.gautami.shipit.model.Category;
import com.gautami.shipit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Category addCategory(@RequestBody Category category){
        return categoryService.add(category);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Category updateCategory(@RequestBody Category category,@PathVariable Long id){
        return categoryService.updateCategory(category,id);
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

}
