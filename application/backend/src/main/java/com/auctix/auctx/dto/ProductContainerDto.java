package com.auctix.auctx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductContainerDto {
    private List<ProductDto> products;
    private List<ProductImageDto> images;
}
