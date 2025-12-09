package com.bus.inventoryService.inventoryService.service;

import java.util.List;

import com.bus.inventoryService.inventoryService.response.EventInventoryResponse;
import com.bus.inventoryService.inventoryService.response.VenueInventoryResponse;

public interface InventoryService {

    List<EventInventoryResponse> getAllEvents();

    VenueInventoryResponse getVenueInformation(Long venueId);

    EventInventoryResponse getEventInventoryById(final Long eventId);

    void updateEventCapacity(final Long eventId, final Long ticketsBooked);

}
