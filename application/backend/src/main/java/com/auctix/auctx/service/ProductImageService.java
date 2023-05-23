package com.auctix.auctx.service;

import com.auctix.auctx.exception.ProductImageNotFoundException;
import com.auctix.auctx.exception.ProductNotFoundException;
import com.auctix.auctx.model.Product;
import com.auctix.auctx.model.ProductImage;
import com.auctix.auctx.repository.ProductImageRepository;
import com.auctix.auctx.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductImageService implements IProductImageService{

    private final ProductImageRepository productImageRepository;

    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductImageService.class);

    @Override
    public ProductImage findByProductId(Long id) {
        return this.productImageRepository.findByProductId(id);
    }

    @Override
    public ProductImage save(ProductImage productImage) {
        logger.info("Saving product image with id {}", productImage.getId());
        Product product = productRepository.findById(productImage.getId()).orElseThrow(ProductNotFoundException::new);
        productRepository.save(product);
        productImage.setProduct(product);
        return this.productImageRepository.save(productImage);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting product image with id {}", id);
        productImageRepository.findById(id).orElseThrow(ProductImageNotFoundException::new);
        this.productImageRepository.deleteById(id);
    }
}
