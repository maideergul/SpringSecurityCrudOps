package com.works.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.works.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}