package com.bibichkov.onlineshop.dao;

import com.bibichkov.onlineshop.entity.*;


public interface ProductDao {
    Iterable<Product> findAll();
}
