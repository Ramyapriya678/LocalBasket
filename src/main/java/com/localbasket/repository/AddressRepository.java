package com.localbasket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localbasket.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}