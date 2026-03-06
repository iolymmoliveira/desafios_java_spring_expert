package com.iolyoliveira.DSCatalog.repositories;

import com.iolyoliveira.DSCatalog.entities.Product;
import com.iolyoliveira.DSCatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoriesTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalOfProducts;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 100000L;
        countTotalOfProducts = 25L;
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);
        product = repository.save(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalOfProducts + 1, product.getId());
    }

    @Test
    public void findByIdShouldReturnAnOptionalNotEmptyProductWhenIdIsPresent() {
        Optional<Product> product = repository.findById(existingId);
        Assertions.assertTrue(product.isPresent());
    }

    @Test
    public void findByIdShouldReturnAnOptionalEmptyProductWhenIdNotExists() {
        Optional<Product> product = repository.findById(nonExistingId);
        Assertions.assertTrue(product.isEmpty());
    }

    @Test
    public void deleteShouldDeleteProductWhenIdExists() {
        repository.deleteById(existingId);
        Assertions.assertFalse(repository.existsById(existingId));
    }

    @Test
    public void deleteShouldDoNothingWhenIdDoesNotExist() {
        Assertions.assertDoesNotThrow(() -> repository.deleteById(nonExistingId));
    }
}
