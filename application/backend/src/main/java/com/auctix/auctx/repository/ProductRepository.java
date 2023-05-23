package com.auctix.auctx.repository;

import com.auctix.auctx.model.Category;
import com.auctix.auctx.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserId(Long userId);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByPriceBetween(Float min, Float max);
    List<Product> findAllByNameContainingIgnoreCase(String name);

}
