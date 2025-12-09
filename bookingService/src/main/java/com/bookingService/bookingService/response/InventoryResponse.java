package com.bookingService.bookingService.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {
    private Long eventId;
    private String name;
    private Long capacity;
    private VenueResponse venue;
    private BigDecimal ticketPrice;

}
