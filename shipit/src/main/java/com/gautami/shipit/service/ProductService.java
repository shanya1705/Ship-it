package com.gautami.shipit.service;


import com.gautami.shipit.dto.ProductDto;
import com.gautami.shipit.exceptions.NotFound;
import com.gautami.shipit.model.Category;
import com.gautami.shipit.model.Product;
import com.gautami.shipit.repository.CategoryRepository;
import com.gautami.shipit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product addProduct(ProductDto productDto) {
        Category category=categoryRepository.findByCategoryName(productDto.getCategory());
        Product product=new Product();
        if(category!=null){
            product.setPrice(productDto.getPrice());
            product.setName(productDto.getName());
            product.setCategory(category);
            return productRepository.save(product);
        }else {
            throw new NotFound("Invalid category- not found");
        }
    }

    public void deleteProduct(Long id) {
       productRepository.deleteById(id);
    }

    public Product updateProduct(ProductDto productDto, Long id) {
        Product existing =productRepository.findById(id).get();
        if(existing==null){
            throw  new NotFound("Product with the id not found: "+id);
        }
        Category category=categoryRepository.findByCategoryName(productDto.getCategory());
        if(category!=null){
            existing.setPrice(productDto.getPrice());
            existing.setName(productDto.getName());
            existing.setCategory(category);
            return productRepository.save(existing);
        }else {
            throw new NotFound("Invalid category- not found");
        }
    }
}
