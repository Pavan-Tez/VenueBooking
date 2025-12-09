package com.venueBooking.orderService.service.serviceImpl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bookingService.bookingService.event.BookingEvent;
import com.venueBooking.orderService.client.InventoryServiceClient;
import com.venueBooking.orderService.entity.Order;
import com.venueBooking.orderService.repository.OrderRepository;
import com.venueBooking.orderService.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final InventoryServiceClient inventoryServiceClient;
    private final OrderRepository orderRepository;

    
    // @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @Override
    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEVent(BookingEvent bookingEvent) {

        log.info("Received order event: {}",bookingEvent);
        
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);

        inventoryServiceClient.updateInventory(order.getEventId(), order.getTicketCount());
        
        log.info("Inventory updated for event: {}, less tickets: {}",order.getEventId(),order.getTicketCount());
    }

    private Order createOrder(BookingEvent bookingEvent){
        return Order.builder()
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

}
