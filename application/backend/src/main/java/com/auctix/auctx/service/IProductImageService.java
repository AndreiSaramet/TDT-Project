package com.auctix.auctx.service;

import com.auctix.auctx.model.ProductImage;

public interface IProductImageService {

    ProductImage findByProductId(Long id);

    ProductImage save(ProductImage productImage);

    void delete(Long id);
}
