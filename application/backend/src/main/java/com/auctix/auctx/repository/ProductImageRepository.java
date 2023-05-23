package com.auctix.auctx.repository;

import com.auctix.auctx.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    ProductImage findByProductId(Long id);
}
