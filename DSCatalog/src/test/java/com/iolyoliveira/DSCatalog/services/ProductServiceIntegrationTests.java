package com.iolyoliveira.DSCatalog.services;

import com.iolyoliveira.DSCatalog.dto.ProductDTO;
import com.iolyoliveira.DSCatalog.repositories.ProductRepository;
import com.iolyoliveira.DSCatalog.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalProducts;

    @BeforeEach
    public void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 100000L;
        countTotalProducts = 25L;
    }

    @Test
    public void findAllPagedShouldReturnPageWhenPage0AndSize10() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ProductDTO> result = service.findAllPaged(pageRequest);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(0, result.getNumber());
        Assertions.assertEquals(10, result.getSize());
        Assertions.assertEquals(countTotalProducts, result.getTotalElements());
    }

    @Test
    public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {
        PageRequest pageRequest = PageRequest.of(23, 10);
        Page<ProductDTO> result = service.findAllPaged(pageRequest);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void findAllPagedShouldReturnSortedPageWhenSortByName() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));
        Page<ProductDTO> result = service.findAllPaged(pageRequest);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals("Macbook Pro",  result.getContent().getFirst().getName());
        Assertions.assertEquals("PC Gamer",  result.getContent().get(1).getName());
        Assertions.assertEquals("PC Gamer Alfa",  result.getContent().get(2).getName());
    }

    @Test
    public void deleteShouldDeleteResourceWhenIdExists() {
        service.delete(existingId);
        Assertions.assertEquals(countTotalProducts - 1, repository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExistingId));
    }
}
