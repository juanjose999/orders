package com.orders.product;

import com.orders.product.dto.ProductRequest;
import com.orders.product.dto.ProductResponse;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static Product toProduct(ProductRequest request) {
        Product product = new Product();
        updateProduct(product, request);
        return product;
    }

    public static void updateProduct(Product product, ProductRequest request) {
        product.setCategoryId(request.categoryId());
        product.setName(request.name());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setSku(request.sku());
        product.setPrice(request.price());
        if (request.stock() != null) {
            product.setStock(request.stock());
        }
        product.setExpirationDate(request.expirationDate());
        if (request.active() != null) {
            product.setActive(request.active());
        }
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getCategoryId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getSku(),
                product.getPrice(),
                product.getStock(),
                product.getExpirationDate(),
                product.getActive(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
