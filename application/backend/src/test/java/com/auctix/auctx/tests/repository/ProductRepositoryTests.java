package com.auctix.auctx.tests.repository;

import com.auctix.auctx.exception.ProductNotFoundException;
import com.auctix.auctx.helpers.ProductConstants;
import com.auctix.auctx.helpers.UserConstants;
import com.auctix.auctx.model.Product;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    private static Users user;
    private static Product product, invalidProduct;
    private static List<Product> productList;

    @PostConstruct
    public static void setUp() {
        user = new Users();
        user.setUsername(UserConstants.VALID_USERNAME);
        user.setEmail(UserConstants.VALID_EMAIL);
        user.setPassword(UserConstants.VALID_PASSWORD);
        user.setRole(UserConstants.VALID_ROLE);


        product = new Product();
        product.setName(ProductConstants.VALID_NAME);
        product.setDescription(ProductConstants.VALID_DESCRIPTION);
        product.setPrice(ProductConstants.VALID_PRICE);
        product.setCategory(ProductConstants.VALID_CATEGORY);
        product.setReviews(new ArrayList<>());
        product.setUser(user);
        invalidProduct = new Product();
        invalidProduct.setName(ProductConstants.INVALID_NAME);
        invalidProduct.setDescription(ProductConstants.INVALID_DESCRIPTION);
        invalidProduct.setPrice(ProductConstants.INVALID_PRICE);
        invalidProduct.setCategory(ProductConstants.INVALID_CATEGORY);
        invalidProduct.setUser(user);
        invalidProduct.setReviews(new ArrayList<>());
        productList = Arrays.asList(product, invalidProduct);
        List<Product> productList1 = Collections.singletonList(product);

        Product addProduct = new Product(ProductConstants.VALID_ADD_ID, ProductConstants.VALID_ADD_NAME, ProductConstants.VALID_DESCRIPTION, ProductConstants.VALID_PRICE, ProductConstants.VALID_ADD_CATEGORY, user, new ArrayList<>());
        Product invalidAddProduct = new Product(ProductConstants.INVALID_ADD_ID, ProductConstants.INVALID_ADD_NAME, ProductConstants.INVALID_DESCRIPTION, ProductConstants.INVALID_PRICE, ProductConstants.INVALID_ADD_CATEGORY, user, new ArrayList<>());
        Product productUpdate = new Product(ProductConstants.VALID_UPDATE_ID, ProductConstants.VALID_UPDATE_NAME, ProductConstants.VALID_DESCRIPTION, ProductConstants.VALID_PRICE, ProductConstants.VALID_UPDATE_CATEGORY, user, new ArrayList<>());
        Product invalidProductUpdate = new Product(ProductConstants.INVALID_UPDATE_ID, ProductConstants.INVALID_UPDATE_NAME, ProductConstants.INVALID_DESCRIPTION, ProductConstants.INVALID_PRICE, ProductConstants.INVALID_UPDATE_CATEGORY, user, new ArrayList<>());


    }

    @Test
    public void testFindAllByUserId() {
        testEntityManager.persist(user);
        testEntityManager.persist(product);
        testEntityManager.persist(invalidProduct);

        List<Product> products = productRepository.findAllByUserId((Long) testEntityManager.getId(user));
        Assertions.assertFalse(productRepository.findAll().isEmpty());
        Assertions.assertEquals(products, productList);
    }

    @Test
    public void testFindAllByCategory() {
        testEntityManager.persist(user);
        testEntityManager.persist(product);
        testEntityManager.persist(invalidProduct);

        List<Product> products = productRepository.findAllByCategory(ProductConstants.VALID_CATEGORY);
        Assertions.assertFalse(productRepository.findAll().isEmpty());
        Assertions.assertEquals(products, productList);
    }

    @Test
    public void testFindAllByPriceBetween() {
        testEntityManager.persist(user);
        testEntityManager.persist(product);
        testEntityManager.persist(invalidProduct);

        List<Product> products = productRepository.findAllByPriceBetween(ProductConstants.PRICE_MIN, ProductConstants.PRICE_MAX);
        Assertions.assertFalse(productRepository.findAll().isEmpty());
        Assertions.assertEquals(products, productList);
    }

    @Test
    public void testFINDAllByNameContainingIgnoreCase() {
        testEntityManager.persist(user);
        testEntityManager.persist(product);
        testEntityManager.persist(invalidProduct);

        List<Product> products = productRepository.findAllByNameContainingIgnoreCase(ProductConstants.PARTIAL_NAME);
        Assertions.assertFalse(productRepository.findAll().isEmpty());
        Assertions.assertEquals(products, productList);
    }
}
