package com.ashtha.slotbooking.service;

import com.ashtha.slotbooking.entity.BookingHistoryEntity;
import com.ashtha.slotbooking.exception.RecordNotFoundException;
import com.ashtha.slotbooking.repository.BookingHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingHistoryEntityService extends BaseCRUDService<BookingHistoryEntity, BookingHistoryRepository> {
    public BookingHistoryEntityService(BookingHistoryRepository repository) {
        super(repository);
    }

    public BookingHistoryEntity getBookingSlotData(String bookingReference){
        return repository.findByBookingReference(bookingReference)
                .orElseThrow(() -> new RecordNotFoundException("api.response.BOOKING_NOT_FOUND.message"));
    }

}
