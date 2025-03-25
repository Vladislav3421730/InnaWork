package com.example.warehouse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Неверный формат email")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Телефон не может быть пустым")
    private String phone;

    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
}
