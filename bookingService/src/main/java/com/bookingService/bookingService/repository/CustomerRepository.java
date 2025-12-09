package com.bookingService.bookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookingService.bookingService.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
