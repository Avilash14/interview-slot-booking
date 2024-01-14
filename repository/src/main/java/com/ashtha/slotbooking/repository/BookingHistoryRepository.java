package com.ashtha.slotbooking.repository;

import com.ashtha.slotbooking.entity.BookingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BookingHistoryRepository extends JpaRepository<BookingHistoryEntity, Long>, JpaSpecificationExecutor<BookingHistoryEntity> {

    Optional<BookingHistoryEntity> findByBookingReference(String bookingReference);

}
