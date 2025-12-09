package com.bookingService.bookingService.service.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingService.bookingService.client.InventoryServiceClient;
import com.bookingService.bookingService.entity.Customer;
import com.bookingService.bookingService.event.BookingEvent;
import com.bookingService.bookingService.repository.CustomerRepository;
import com.bookingService.bookingService.request.BookingRequest;
import com.bookingService.bookingService.response.BookingResponse;
import com.bookingService.bookingService.response.InventoryResponse;
import com.bookingService.bookingService.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    @Autowired
    public BookingServiceImpl(CustomerRepository customerRepository, InventoryServiceClient inventoryServiceClient, InventoryServiceClient inventoryServiceClient2,KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient2;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public BookingResponse createBooking(final BookingRequest request) {

        
        //check if user exists
        //check if there is enough inventory
        //-- get event information to also get venue information
        // create booking record
        // send booking to order service on a kafka topic
        final Customer customer = customerRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        if(inventoryResponse.getCapacity() < request.getTicketCount()) {
            throw new RuntimeException("Not enough tickets available");
        }

        log.info("Inventory Response: {}", inventoryResponse);

        final BookingEvent bookingEvent = createBookingEvent(request, customer, inventoryResponse);

        kafkaTemplate.send("booking", bookingEvent);
        log.info("Booking sent to kafka: {}",bookingEvent);
        

        return BookingResponse.builder()
        .userId(bookingEvent.getUserId())
        .eventId(bookingEvent.getEventId())
        .ticketCount(bookingEvent.getTicketCount())
        .totalPrice(bookingEvent.getTotalPrice())
        .build();
    }
    private BookingEvent createBookingEvent(final BookingRequest request,
                                            final Customer customer,
                                            final InventoryResponse inventoryResponse 
        ){
            return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(request.getEventId())
                .ticketCount(request.getTicketCount())
                .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())))
                .build();
        }
    
}
