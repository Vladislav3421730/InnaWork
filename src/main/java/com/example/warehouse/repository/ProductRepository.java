package com.example.warehouse.repository;

import com.example.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:search% ORDER BY " +
            "CASE WHEN :sort = 'price' AND :order = 'asc' THEN p.price END ASC, " +
            "CASE WHEN :sort = 'price' AND :order = 'desc' THEN p.price END DESC, " +
            "CASE WHEN :sort = 'name' AND :order = 'asc' THEN p.name END ASC, " +
            "CASE WHEN :sort = 'name' AND :order = 'desc' THEN p.name END DESC, " +
            "CASE WHEN :sort = 'id' AND :order = 'asc' THEN p.id END ASC, " +
            "CASE WHEN :sort = 'id' AND :order = 'desc' THEN p.id END DESC")
    List<Product> findByNameContainingAndSort(@Param("search") String search,
                                              @Param("sort") String sort,
                                              @Param("order") String order);
}
