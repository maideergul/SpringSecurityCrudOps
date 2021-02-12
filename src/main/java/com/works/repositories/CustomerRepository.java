package com.works.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.works.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}