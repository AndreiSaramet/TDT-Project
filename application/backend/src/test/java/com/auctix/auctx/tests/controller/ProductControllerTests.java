package com.auctix.auctx.tests.controller;


import com.auctix.auctx.controller.ProductController;
import com.auctix.auctx.converter.ProductConverter;
import com.auctix.auctx.dto.ProductDto;
import com.auctix.auctx.exception.ProductNotFoundException;
import com.auctix.auctx.helpers.ProductConstants;
import com.auctix.auctx.helpers.UserConstants;
import com.auctix.auctx.jwtutils.JwtFilter;
import com.auctix.auctx.model.Product;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private static ProductService productService;

    @MockBean
    private static ProductConverter productConverter;

    @MockBean
    private PasswordEncoder crypt;

    @MockBean
    private JwtFilter jwtFilter;

    private static ProductDto productDto, invalidProductDto;
    private static List<ProductDto> productDtoList, productDtoList1;
    private final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

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


        Product product = new Product(ProductConstants.VALID_ID, ProductConstants.VALID_NAME, ProductConstants.VALID_DESCRIPTION, ProductConstants.VALID_PRICE, ProductConstants.VALID_CATEGORY, user, new ArrayList<>());
        Product invalidProduct = new Product(ProductConstants.INVALID_ID, ProductConstants.INVALID_NAME, ProductConstants.INVALID_DESCRIPTION, ProductConstants.INVALID_PRICE, ProductConstants.INVALID_CATEGORY, invalidUser, new ArrayList<>());
        productDto = new ProductDto(ProductConstants.VALID_ID, ProductConstants.VALID_NAME, ProductConstants.VALID_DESCRIPTION, ProductConstants.VALID_PRICE, ProductConstants.VALID_CATEGORY, user.getId());
        invalidProductDto = new ProductDto(ProductConstants.INVALID_ID, ProductConstants.INVALID_NAME, ProductConstants.INVALID_DESCRIPTION, ProductConstants.INVALID_PRICE, ProductConstants.INVALID_CATEGORY, invalidUser.getId());

        List<Product> productList = Arrays.asList(product, invalidProduct);
        List<Product> productList1 = Collections.singletonList(product);
        productDtoList = Arrays.asList(productDto, invalidProductDto);
        productDtoList1 = Collections.singletonList(productDto);
        when(productService.addProduct(product)).thenReturn(product);
        when(productService.updateProduct(product)).thenReturn(product);
        when(productService.updateProduct(invalidProduct)).thenThrow(ProductNotFoundException.class);
        doNothing().when(productService).deleteProduct(ProductConstants.VALID_ID);
        doThrow(ProductNotFoundException.class).when(productService).deleteProduct(ProductConstants.INVALID_ID);
        when(productService.getProductById(ProductConstants.VALID_ID)).thenReturn(product);
        when(productService.getProductById(ProductConstants.INVALID_ID)).thenThrow(ProductNotFoundException.class);
        when(productService.getAllProductByPartialName(ProductConstants.PARTIAL_NAME)).thenReturn(productList);
        when(productService.getAllProductsByPriceRange(ProductConstants.PRICE_MIN, ProductConstants.PRICE_MAX)).thenReturn(productList);
        when(productService.getAllProductsByCategory(ProductConstants.VALID_CATEGORY)).thenReturn(productList);
        when(productService.getAllProductsByUserId(UserConstants.VALID_ID)).thenReturn(productList1);
        when(productService.getAllProducts()).thenReturn(productList);

        when(productConverter.convertDtoToModel(productDto)).thenReturn(product);
        when(productConverter.convertModelToDto(product)).thenReturn(productDto);
        when(productConverter.convertDtoToModel(invalidProductDto)).thenReturn(invalidProduct);
        when(productConverter.convertModelToDto(invalidProduct)).thenReturn(invalidProductDto);
        when(productConverter.convertModelListToDtoList(productList)).thenReturn(productDtoList);
        when(productConverter.convertModelListToDtoList(productList1)).thenReturn(productDtoList1);

    }

    @Test
    public void testAddProduct() throws Exception {
        mockMvc.perform(post("/addProduct").contentType(MediaType.APPLICATION_JSON).content(this.ow.writeValueAsString(productDto)).accept("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ProductConstants.VALID_ID)).andExpect(jsonPath("$.name").value(ProductConstants.VALID_NAME)).andExpect(jsonPath("$.description").value(ProductConstants.VALID_DESCRIPTION)).andExpect(jsonPath("$.price").value(ProductConstants.VALID_PRICE)).andExpect(jsonPath("$.category").value(ProductConstants.VALID_CATEGORY.toString())).andExpect(jsonPath("$.userId").value(UserConstants.VALID_ID));

    }

    @Test
    public void testUpdateValidProduct() throws Exception {
        mockMvc.perform(put("/updateProduct").contentType(MediaType.APPLICATION_JSON).content(this.ow.writeValueAsString(productDto)).accept("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ProductConstants.VALID_ID)).andExpect(jsonPath("$.name").value(ProductConstants.VALID_NAME)).andExpect(jsonPath("$.description").value(ProductConstants.VALID_DESCRIPTION)).andExpect(jsonPath("$.price").value(ProductConstants.VALID_PRICE)).andExpect(jsonPath("$.category").value(ProductConstants.VALID_CATEGORY.toString())).andExpect(jsonPath("$.userId").value(UserConstants.VALID_ID));

    }

    @Test
    public void testUpdateInvalidProduct() throws Exception {
        mockMvc.perform(put("/updateProduct").contentType(MediaType.APPLICATION_JSON).content(this.ow.writeValueAsString(invalidProductDto)).accept("application/json")).andExpect(status().isNotFound())
                .andExpect(content().string("Product not found"));

    }

    @Test
    public void testDeleteValidProduct() throws Exception {
        mockMvc.perform(delete("/deleteProduct/{id}", ProductConstants.VALID_ID).accept("application/json")).andExpect(status().isOk())
        ;

    }

    @Test
    public void testDeleteInvalidProduct() throws Exception {
        mockMvc.perform(delete("/deleteProduct/{id}", ProductConstants.INVALID_ID).accept("application/json")).andExpect(status().isNotFound())
                .andExpect(content().string("Product not found"));

    }

    @Test
    public void testFindProductByValidId() throws Exception {
        mockMvc.perform(get("/findProductById/{id}", ProductConstants.VALID_ID).accept("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ProductConstants.VALID_ID));

    }

    @Test
    public void testFindProductByInvalidId() throws Exception {
        mockMvc.perform(get("/findProductById/{id}", ProductConstants.INVALID_ID).accept("application/json")).andExpect(status().isNotFound())
                .andExpect(content().string("Product not found"));

    }

    @Test
    public void testFindProductsByPartialName() throws Exception {
        mockMvc.perform(get("/findProductsByPartialName/{name}", ProductConstants.PARTIAL_NAME).accept("application/json")).andExpect(status().isOk())
                .andExpect(content().json(this.ow.writeValueAsString(productDtoList)));

    }

    @Test
    public void testFindProductsByPriceRange() throws Exception {
        mockMvc.perform(get("/findProductsByPriceRange/{min}/{max}", ProductConstants.PRICE_MIN, ProductConstants.PRICE_MAX).accept("application/json")).andExpect(status().isOk())
                .andExpect(content().json(this.ow.writeValueAsString(productDtoList)));

    }

    @Test
    public void testFindProductsByCategory() throws Exception {
        mockMvc.perform(get("/findProductsByCategory/{category}", ProductConstants.VALID_CATEGORY).accept("application/json")).andExpect(status().isOk())
                .andExpect(content().json(this.ow.writeValueAsString(productDtoList)));

    }

    @Test
    public void testFindProductsByUserId() throws Exception {
        mockMvc.perform(get("/findProductsByUserId/{userId}", UserConstants.VALID_ID).accept("application/json")).andExpect(status().isOk())
                .andExpect(content().json(this.ow.writeValueAsString(productDtoList1)));

    }


}
