package com.ashtha.slotbooking.service.booking;

import com.ashtha.slotbooking.model.request.booking.SlotBookingRequest;
import com.ashtha.slotbooking.model.response.booking.BookingDetailsResponse;
import com.ashtha.slotbooking.model.response.booking.SlotBookingResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingService {

    @Transactional
    SlotBookingResponse bookSlot(SlotBookingRequest request);

    @Transactional
    SlotBookingResponse updateBooking(String bookingReference, SlotBookingRequest request);

    @Transactional
    void cancelBooking(String bookingReference);

    @Transactional(readOnly = true)
    List<SlotBookingResponse> getAllBookings();

    @Transactional(readOnly = true)
    BookingDetailsResponse getBookingDetails(String bookingReference);

}
