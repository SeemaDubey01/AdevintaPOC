package com.adevinta.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adevinta.poc.model.Customer;

@Repository
public interface AdevintaPocRepo extends JpaRepository<Customer, Integer> {

	List<Customer> findAllByName(String name);

}
