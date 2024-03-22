package com.nycweather.productservice.repository;

import com.nycweather.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> findByProductName(String name);

}
