package com.works.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.works.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}