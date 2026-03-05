package com.iolyoliveira.DSCatalog.repositories;

import com.iolyoliveira.DSCatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
