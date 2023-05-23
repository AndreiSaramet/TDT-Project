package com.auctix.auctx.service;

import com.auctix.auctx.exception.ProductAlreadyExistingException;
import com.auctix.auctx.exception.ProductNotFoundException;
import com.auctix.auctx.exception.UserNotFoundException;
import com.auctix.auctx.model.Category;
import com.auctix.auctx.model.Product;
import com.auctix.auctx.repository.ProductRepository;
import com.auctix.auctx.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    private final UsersRepository usersRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Override
    public List<Product> getAllProducts() {
        logger.info("getAllProducts");
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        logger.info("addProduct: " + product.getName());
        this.usersRepository.findById(product.getUser().getId()).orElseThrow(UserNotFoundException::new);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        logger.info("updateProduct: " + product.getId());
        productRepository.findById(product.getId()).orElseThrow(ProductNotFoundException::new);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        logger.info("deleteProduct: " + id);
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        logger.info("getProductById: " + id);
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> getAllProductsByUserId(Long userId) {
        logger.info("getAllProductsByUserId: " + userId);
        return productRepository.findAllByUserId(userId);
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) {
        logger.info("getAllProductsByCategory: " + category);
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> getAllProductsByPriceRange(Float min, Float max) {
        logger.info("getAllProductsByPriceRange: " + min + " - " + max);
        return productRepository.findAllByPriceBetween(min, max);
    }

    @Override
    public List<Product> getAllProductByPartialName(String name) {
        logger.info("getAllProductByPartialName: " + name);
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }
}
