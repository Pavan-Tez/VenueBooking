package com.bus.inventoryService.inventoryService.response;

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
    private String name;
    private Long capacity;
    private Venue venue;
}
