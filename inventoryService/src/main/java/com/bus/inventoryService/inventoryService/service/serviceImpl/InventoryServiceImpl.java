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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
                .eventId(event.getId())
                .ticketPrice(event.getTicketPrice())
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
            .address(venue.getAddress())
            .build(); 
    
    }

    @Override
    public EventInventoryResponse getEventInventoryById(final Long eventId) {
        final Event event = eventRepository.findById(eventId).orElse(null);
        
        return EventInventoryResponse.builder()
            .name(event.getName())
            .capacity(event.getTotalCapacity())
            .venue(event.getVenue())
            .ticketPrice(event.getTicketPrice())
            .eventId(event.getId())
            .build();
    }

    @Override
    public void updateEventCapacity(Long eventId, Long ticketsBooked) {

        final Event event = eventRepository.findById(eventId).orElse(null);
        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepository.saveAndFlush(event);
        log.info("Updated event capacity for event id: {} with ticketss booked:{}",eventId,ticketsBooked);
    }
    
    
}
