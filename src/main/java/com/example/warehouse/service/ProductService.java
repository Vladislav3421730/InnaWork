package com.example.warehouse.service;

import com.example.warehouse.entity.Product;
import com.example.warehouse.exception.ProductNotFoundException;
import com.example.warehouse.repository.ProductRepository;

import com.example.warehouse.utils.ReceiveSubstring;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll(String search, String sort) {
        return productRepository.findByNameContainingAndSort(search,
                ReceiveSubstring.extractSubstringBeforeUnderscore(sort),
                ReceiveSubstring.extractSubstringAfterUnderscore(sort));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Товар с id %d не найден", id)));
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(String.format("Товар с id %d не найден", id));
        }
        productRepository.deleteById(id);
    }

}
