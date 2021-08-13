package com.bibichkov.onlineshop.dao;

import com.bibichkov.onlineshop.entity.*;


public interface ProductDao {
    Iterable<Product> findAll();
    void add(Product product);
    Product getById(Long productId);
    void delete(Long id);
    void update(Product product);
}
