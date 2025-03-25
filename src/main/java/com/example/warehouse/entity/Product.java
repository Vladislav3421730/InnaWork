package com.example.warehouse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Название продукта не может быть пустым")
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(500)")
    private String description;

    @NotNull(message = "Количество не может быть пустым")
    @Min(value = 0, message = "Количество должно быть больше или равно 0")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Цена не может быть пустой")
    @Min(value = 0, message = "Цена должна быть положительным числом")
    @Column(nullable = false)
    private Double price;
}
