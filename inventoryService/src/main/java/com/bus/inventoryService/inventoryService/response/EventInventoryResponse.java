package com.bus.inventoryService.inventoryService.response;

import java.math.BigDecimal;

import com.bus.inventoryService.inventoryService.Entity.Venue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryResponse {
    private Long eventId;
    private String name;
    private Long capacity;
    private Venue venue;
    private BigDecimal ticketPrice;
    private Long leftCapacity;
}
