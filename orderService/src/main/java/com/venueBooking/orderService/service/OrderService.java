package com.venueBooking.orderService.service;

import com.bookingService.bookingService.event.BookingEvent;

public interface OrderService {

    public void orderEVent(BookingEvent bookingEvent);
}
