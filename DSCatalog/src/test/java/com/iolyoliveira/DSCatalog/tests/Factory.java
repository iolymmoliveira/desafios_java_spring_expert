package com.iolyoliveira.DSCatalog.tests;

import com.iolyoliveira.DSCatalog.dto.ProductDTO;
import com.iolyoliveira.DSCatalog.entities.Category;
import com.iolyoliveira.DSCatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct() {
        Product product = new Product(
                1L,
                "SmarthPhone",
                "Phone with smart technology",
                1800.00,
                "",
                Instant.parse("2026-03-06T14:42:20Z")
        );
        product.getCategories().add(new Category(2L, "Electronics"));
        return product;
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}
