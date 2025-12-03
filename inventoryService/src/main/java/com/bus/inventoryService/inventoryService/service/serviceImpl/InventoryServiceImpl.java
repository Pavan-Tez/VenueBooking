package com.bus.inventoryService.inventoryService.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.inventoryService.inventoryService.Entity.Event;
import com.bus.inventoryService.inventoryService.Entity.Venue;
import com.bus.inventoryService.inventoryService.repository.EventRepository;
import com.bus.inventoryService.inventoryService.repository.VenueRepository;
import com.bus.inventoryService.inventoryService.response.EventInventoryResponse;
import com.bus.inventoryService.inventoryService.response.VenueInventoryResponse;
import com.bus.inventoryService.inventoryService.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    
    // @Autowired
     public InventoryServiceImpl(EventRepository eventRepository,
                                VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public List<EventInventoryResponse> getAllEvents() {
        // TODO Auto-generated method stub
        final List<Event> events = eventRepository.findAll();
        
        return events.stream()
                .map(event -> EventInventoryResponse.builder()
                .name(event.getName())
                .capacity(event.getTotalCapacity())
                .venue(event.getVenue())
                .build())
                .collect(Collectors.toList());
    
    }

    @Override
    public VenueInventoryResponse getVenueInformation(Long venueId) {
        
        final Venue venue = venueRepository.findById(venueId).orElse(null);

        return VenueInventoryResponse.builder()
            .venueId(venue.getId())
            .venueName(venue.getName())
            .totalCapacity(venue.getTotalCapacity())
            .build(); 
    
    }
    
    
}
