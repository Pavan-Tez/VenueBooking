package com.bookingService.bookingService.service;

import com.bookingService.bookingService.request.BookingRequest;
import com.bookingService.bookingService.response.BookingResponse;

public interface BookingService {

    public BookingResponse createBooking(BookingRequest request) ;
    
}
