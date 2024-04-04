package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(nativeQuery = true,
            value = "SELECT products.name FROM products " +
            "INNER JOIN providers ON providers.id = products.id_providers " +
            "WHERE providers.name LIKE CONCAT(:name, '%') " +
            "AND products.amount BETWEEN :min AND :max")
    List<ProductMinProjection> findProductByProducerAndQuantitySQL(String name, Integer min, Integer max);

    @Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(p.name) " +
            "FROM Product p " +
                    "WHERE p.provider.name LIKE CONCAT(:name, '%') " +
                    "AND p.amount BETWEEN :min AND :max")
    List<ProductMinDTO> findProductByProducerAndQuantityJPQL(String name, Integer min, Integer max);
}
