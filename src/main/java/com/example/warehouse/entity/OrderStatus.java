package com.example.warehouse.entity;

public enum OrderStatus {
    PENDING("В ожидании"),
    PROCESSING("Обрабатывается"),
    SHIPPED("Отправлен"),
    DELIVERED("Доставлен"),
    CANCELED("Отменён");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
