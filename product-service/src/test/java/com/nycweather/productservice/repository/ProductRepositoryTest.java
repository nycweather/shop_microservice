package com.nycweather.productservice.repository;

import com.nycweather.productservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void ProductRepository_findByProductName_getProductsGivenName() {
        // Given
        String product1 = "Product 1";
        String product2 = "Product 2";

        Product product_1 = new Product(
                "abcd-1234",
                product1,
                "Item does things",
                10.49);
        Product product_2 = new Product(
                "abcd-5678",
                product2,
                "Item does things",
                10.49);

        productRepository.save(product_1);
        productRepository.save(product_2);
        // When
        var products = productRepository.findByProductName("Product");
        // Then
        assertThat(products).hasSize(2);
    }

    @Test
    void ProductRepository_findByProductName_getNoProductWhenNoProductInDB() {
        // Given
        int productCount = 0;
        // When
        var products = productRepository.findByProductName("Product");
        // Then
        assertThat(products).hasSize(productCount);
    }
}