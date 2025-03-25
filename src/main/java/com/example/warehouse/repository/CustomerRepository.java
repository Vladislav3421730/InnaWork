package com.example.warehouse.repository;

import com.example.warehouse.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByNameIsContainingIgnoreCase(String name);
}
