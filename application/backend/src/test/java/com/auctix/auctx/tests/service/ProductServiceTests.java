package com.auctix.auctx.tests.service;


import com.auctix.auctx.exception.ProductNotFoundException;

import com.auctix.auctx.helpers.ProductConstants;
import com.auctix.auctx.helpers.UserConstants;
import com.auctix.auctx.model.Product;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.repository.ProductRepository;

import com.auctix.auctx.service.ProductService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTests {

    @MockBean
    private static ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private static Product product, productUpdate, invalidProductUpdate, addProduct;
    private static List<Product> productList;

    @PostConstruct
    public static void setUp() {
        Users user = new Users();
        user.setUsername(UserConstants.VALID_USERNAME);
        user.setId(UserConstants.VALID_ID);
        user.setEmail(UserConstants.VALID_EMAIL);
        user.setPassword(UserConstants.VALID_PASSWORD);
        user.setRole(UserConstants.VALID_ROLE);
        Users invalidUser = new Users();
        invalidUser.setUsername(UserConstants.INVALID_USERNAME);
        invalidUser.setId(UserConstants.INVALID_ID);
        invalidUser.setEmail(UserConstants.INVALID_EMAIL);
        invalidUser.setPassword(UserConstants.INVALID_PASSWORD);
        invalidUser.setRole(UserConstants.INVALID_ROLE);
        product = new Product(ProductConstants.VALID_ID, ProductConstants.VALID_NAME, ProductConstants.VALID_DESCRIPTION, ProductConstants.VALID_PRICE, ProductConstants.VALID_CATEGORY, user, new ArrayList<>());
        Product invalidProduct = new Product(ProductConstants.INVALID_ID, ProductConstants.INVALID_NAME, ProductConstants.INVALID_DESCRIPTION, ProductConstants.INVALID_PRICE, ProductConstants.INVALID_CATEGORY, invalidUser, new ArrayList<>());

        productList = Arrays.asList(product, invalidProduct);
        List<Product> productList1 = Collections.singletonList(product);

        addProduct = new Product(ProductConstants.VALID_ADD_ID, ProductConstants.VALID_ADD_NAME, ProductConstants.VALID_DESCRIPTION, ProductConstants.VALID_PRICE, ProductConstants.VALID_ADD_CATEGORY, user, new ArrayList<>());
        Product invalidAddProduct = new Product(ProductConstants.INVALID_ADD_ID, ProductConstants.INVALID_ADD_NAME, ProductConstants.INVALID_DESCRIPTION, ProductConstants.INVALID_PRICE, ProductConstants.INVALID_ADD_CATEGORY, user, new ArrayList<>());
        productUpdate = new Product(ProductConstants.VALID_UPDATE_ID, ProductConstants.VALID_UPDATE_NAME, ProductConstants.VALID_DESCRIPTION, ProductConstants.VALID_PRICE, ProductConstants.VALID_UPDATE_CATEGORY, user, new ArrayList<>());
        invalidProductUpdate = new Product(ProductConstants.INVALID_UPDATE_ID, ProductConstants.INVALID_UPDATE_NAME, ProductConstants.INVALID_DESCRIPTION, ProductConstants.INVALID_PRICE, ProductConstants.INVALID_UPDATE_CATEGORY, user, new ArrayList<>());

        when(productRepository.findAll()).thenReturn(productList);
        when(productRepository.save(addProduct)).thenReturn(addProduct);
        when(productRepository.findById(ProductConstants.VALID_UPDATE_ID)).thenReturn(Optional.ofNullable(productUpdate));
        when(productRepository.findById(ProductConstants.INVALID_UPDATE_ID)).thenThrow(ProductNotFoundException.class);
        when(productRepository.save(productUpdate)).thenReturn(productUpdate);
        when(productRepository.findById(ProductConstants.VALID_ID)).thenReturn(Optional.ofNullable(product));
        when(productRepository.findById(ProductConstants.INVALID_ID)).thenThrow(ProductNotFoundException.class);
        doNothing().when(productRepository).deleteById(ProductConstants.VALID_ID);
        doThrow(ProductNotFoundException.class).when(productRepository).deleteById(ProductConstants.INVALID_ID);

        when(productRepository.findAllByUserId(UserConstants.VALID_ID)).thenReturn(productList);
        when(productRepository.findAllByCategory(ProductConstants.VALID_CATEGORY)).thenReturn(productList);
        when(productRepository.findAllByPriceBetween(ProductConstants.PRICE_MIN, ProductConstants.PRICE_MAX)).thenReturn(productList);
        when(productRepository.findAllByNameContainingIgnoreCase(ProductConstants.PARTIAL_NAME)).thenReturn(productList);


    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(products, productList);
    }

    @Test
    public void testAddProduct() {
        Product product1 = productService.addProduct(ProductServiceTests.addProduct);
        Assertions.assertNotNull(product1);
        Assertions.assertEquals(product1, ProductServiceTests.addProduct);
    }

    @Test
    public void testUpdateValidProduct() {
        Product product1 = productService.addProduct(ProductServiceTests.productUpdate);
        Assertions.assertNotNull(product1);
        Assertions.assertEquals(product1, ProductServiceTests.productUpdate);
    }

    @Test
    public void testUpdateInvalidProduct() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(ProductServiceTests.invalidProductUpdate));

    }

    @Test
    public void testDeleteValidProduct() {
        productService.deleteProduct(ProductConstants.VALID_ID);
        verify(productRepository, times(1)).findById(ProductConstants.VALID_ID);
        verify(productRepository, times(1)).deleteById(ProductConstants.VALID_ID);


    }

    @Test
    public void testDeleteInvalidProduct() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(ProductConstants.INVALID_ID));


    }

    @Test
    public void testGetValidProductById() {
        Product product1 = productService.getProductById(ProductConstants.VALID_ID);
        Assertions.assertNotNull(product1);
        Assertions.assertEquals(product1, ProductServiceTests.product);
    }

    @Test
    public void testGetInvalidProductById() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productRepository.findById(ProductConstants.INVALID_ID));

    }

    @Test
    public void testGetAllProductsByUserId() {
        List<Product> products = productService.getAllProductsByUserId(UserConstants.VALID_ID);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(products, productList);
    }

    @Test
    public void testGetAllProductsByCategory() {
        List<Product> products = productService.getAllProductsByCategory(ProductConstants.VALID_CATEGORY);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(products, productList);
    }

    @Test
    public void testGetAllProductsByPriceRange() {
        List<Product> products = productService.getAllProductsByPriceRange(ProductConstants.PRICE_MIN, ProductConstants.PRICE_MAX);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(products, productList);
    }

    @Test
    public void testGetAllProductsByPartialName() {
        List<Product> products = productService.getAllProductByPartialName(ProductConstants.PARTIAL_NAME);
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(products, productList);
    }
}
