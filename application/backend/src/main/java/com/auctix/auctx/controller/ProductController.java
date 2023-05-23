package com.auctix.auctx.controller;

import com.auctix.auctx.converter.ProductConverter;
import com.auctix.auctx.dto.ProductDto;
import com.auctix.auctx.model.Category;
import com.auctix.auctx.model.Product;
import com.auctix.auctx.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @PostMapping("/addProduct")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        Product product = productConverter.convertDtoToModel(productDto);
        Product addedProduct = productService.addProduct(product);
        return productConverter.convertModelToDto(addedProduct);
    }

    @PutMapping("/updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productConverter.convertDtoToModel(productDto);
        Product updatedProduct = productService.updateProduct(product);
        return productConverter.convertModelToDto(updatedProduct);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/findProductById/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return productConverter.convertModelToDto(product);
    }

    @GetMapping("/findProductsByPartialName/{name}")
    public List<ProductDto> findProductByName(@PathVariable String name) {
        List<Product> products = productService.getAllProductByPartialName(name);
        return productConverter.convertModelListToDtoList(products);
    }

    @GetMapping("/findProductsByPriceRange/{min}/{max}")
    public List<ProductDto> findProductByPriceRange(@PathVariable Float min, @PathVariable Float max) {
        List<Product> products = productService.getAllProductsByPriceRange(min, max);
        return productConverter.convertModelListToDtoList(products);
    }

    @GetMapping("/findProductsByCategory/{category}")
    public List<ProductDto> findProductByCategory(@PathVariable Category category) {
        List<Product> products = productService.getAllProductsByCategory(category);
        return productConverter.convertModelListToDtoList(products);
    }

    @GetMapping("/findProductsByUserId/{userId}")
    public List<ProductDto> findProductByUserId(@PathVariable Long userId) {
        List<Product> products = productService.getAllProductsByUserId(userId);
        return productConverter.convertModelListToDtoList(products);
    }

    @GetMapping("/findAllProducts")
    public List<ProductDto> findAllProducts() {
        List<Product> products = productService.getAllProducts();
        return productConverter.convertModelListToDtoList(products);
    }
}
