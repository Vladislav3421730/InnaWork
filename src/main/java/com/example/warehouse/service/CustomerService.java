package com.example.warehouse.service;

import com.example.warehouse.entity.Customer;
import com.example.warehouse.exception.CustomerNotFoundException;
import com.example.warehouse.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> findAll(String search) {
        return customerRepository.findAllByNameIsContainingIgnoreCase(search);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException(String.format("Контрагент с id %d не найден", id)));
    }

}
