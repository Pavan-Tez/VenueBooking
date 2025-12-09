package com.venueBooking.orderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venueBooking.orderService.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
