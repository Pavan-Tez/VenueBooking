package com.bookingService.bookingService.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookingService.bookingService.response.InventoryResponse;


@Service
public class InventoryServiceClient {
    
    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryResponse getInventory(final Long eventId) {
        //call to inventory service
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl+"/event/"+eventId, InventoryResponse.class);
    }
}
