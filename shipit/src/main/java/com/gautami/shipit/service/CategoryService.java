package com.gautami.shipit.service;

import com.gautami.shipit.exceptions.NotFound;
import com.gautami.shipit.model.Category;
import com.gautami.shipit.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Category category, Long id) {
        Category existing=categoryRepository.findById(id).get();
        if(existing==null){
            throw  new NotFound("Category with the id not found: "+id);
        }
         existing.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existing);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
