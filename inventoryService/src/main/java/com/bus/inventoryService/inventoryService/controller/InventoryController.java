package com.bus.inventoryService.inventoryService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.inventoryService.inventoryService.response.EventInventoryResponse;
import com.bus.inventoryService.inventoryService.response.VenueInventoryResponse;
import com.bus.inventoryService.inventoryService.service.InventoryService;

import java.util.List;

import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1")
public class InventoryController {
    
    private InventoryService inventoryService;

    
    // @Autowired
    //if only constructor no need to put autowired
    public InventoryController(final InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }



    @GetMapping("/inventory/events")
    public @ResponseBody List<EventInventoryResponse> inventoryGetAllEvents(){
        return inventoryService.getAllEvents();
    }

    @GetMapping("/inventory/venue/{venueId}")
    public @ResponseBody VenueInventoryResponse venueInventoryById(@PathVariable("venueId") Long venueId){
        return inventoryService.getVenueInformation(venueId);
    }
    
    @GetMapping("/inventory/event/{eventId}")
    public @ResponseBody EventInventoryResponse inventoryForEvent(@PathVariable("eventId") Long eventId) {
        return inventoryService.getEventInventoryById(eventId);
    }

    @PutMapping("/inventory/event/{eventId}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable("eventId") Long eventId, @PathVariable("capacity") Long capacity) {

       inventoryService.updateEventCapacity(eventId, capacity);
        
        return ResponseEntity.ok().build();
    }
    
    
}
