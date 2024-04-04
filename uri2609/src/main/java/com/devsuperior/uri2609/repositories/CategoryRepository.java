package com.devsuperior.uri2609.repositories;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2609.entities.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true,
            value = "SELECT c.name, SUM(p.amount) " +
                    "FROM products AS p " +
                    "INNER JOIN categories AS c " +
                    "ON p.id_categories = c.id " +
                    "GROUP BY c.name")
    List<CategorySumProjection> groupQuantityByCategorySQL();

    @Query("SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(p.category.name, SUM(p.amount)) " +
                    "FROM Product p " +
                    "GROUP BY p.category.name")
    List<CategorySumDTO> groupQuantityByCategoryJPQL();

}
