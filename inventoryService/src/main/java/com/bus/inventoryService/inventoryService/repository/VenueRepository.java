package com.bus.inventoryService.inventoryService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.inventoryService.inventoryService.Entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

}
