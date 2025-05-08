package com.example.warehouse.service;

import com.example.warehouse.entity.*;
import com.example.warehouse.exception.OrderNotFoundException;
import com.example.warehouse.exception.ProductNotFoundException;
import com.example.warehouse.exception.UserNotFoundException;
import com.example.warehouse.repository.CustomerRepository;
import com.example.warehouse.repository.OrderRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void save(Principal principal,  Long customerId, List<Long> productIds, List<Integer> quantities) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("Покупатель не найден"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Покупатель с %d не найден", customerId)));

        Order order = new Order();
        order.setCustomer(customer);
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (int i = 0; i < productIds.size(); i++) {
            Product product = productRepository.findById(productIds.get(i))
                    .orElseThrow(() -> new ProductNotFoundException("Товар с  не найден"));

            int quantity = quantities.get(i);

            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            double itemPrice = product.getPrice() * quantity;
            totalPrice += itemPrice;

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setPrice(totalPrice);

        orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException(String.format("Заказ с id $%d не найден", id)));
    }

    @Transactional
    public void update(Long id, String status) {
       Order order = orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException(String.format("Заказ с id $%d не найден", id)));
       order.setStatus(OrderStatus.valueOf(status));
       orderRepository.save(order);
    }


}
