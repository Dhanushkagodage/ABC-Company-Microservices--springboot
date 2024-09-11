package org.example.product.repository;

import org.example.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from Product where product_id=?1",nativeQuery = true)
    Product getProductById(Integer productId);
}
