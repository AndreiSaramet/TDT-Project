package com.auctix.auctx.service;

import com.auctix.auctx.model.Category;
import com.auctix.auctx.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProductsByUserId(Long userId);
    List<Product> getAllProductsByCategory(Category category);
    List<Product> getAllProductsByPriceRange(Float min, Float max);
    List<Product> getAllProductByPartialName(String name);
}
