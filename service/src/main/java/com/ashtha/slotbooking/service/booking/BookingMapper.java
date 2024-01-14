package com.ashtha.slotbooking.service.booking;


import com.ashtha.slotbooking.entity.BookingHistoryEntity;
import com.ashtha.slotbooking.enums.BookingStatus;
import com.ashtha.slotbooking.model.booking.BookingValidationDto;
import com.ashtha.slotbooking.model.booking.UpdateBookingValidationDto;
import com.ashtha.slotbooking.model.response.booking.BookingDetailsResponse;
import com.ashtha.slotbooking.model.response.booking.SlotBookingResponse;
import com.ashtha.slotbooking.service.slot.SlotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final SlotMapper slotMapper;

    public BookingHistoryEntity mapToEntity(BookingValidationDto dto){
        BookingHistoryEntity bookingHistoryEntity = new BookingHistoryEntity();

        bookingHistoryEntity.setBookingReference(UUID.randomUUID().toString().replace("-",""));
        bookingHistoryEntity.setSlot(dto.getSlotEntity());
        bookingHistoryEntity.setIntervieweeEmail(dto.getIntervieweeEmail());
        bookingHistoryEntity.setIntervieweeName(dto.getIntervieweeName());
        bookingHistoryEntity.setIntervieweeMobileNo(dto.getIntervieweeMobileNo());
        bookingHistoryEntity.setIntervieweeResumeUrl(dto.getIntervieweeResumeUrl());
        bookingHistoryEntity.setStatus(BookingStatus.BOOKED);

        return bookingHistoryEntity;

    }

    public BookingHistoryEntity mapToEntity(UpdateBookingValidationDto dto, BookingHistoryEntity bookingHistoryEntity){

        bookingHistoryEntity.setSlot(dto.getSlotEntity());
        bookingHistoryEntity.setIntervieweeEmail(dto.getIntervieweeEmail());
        bookingHistoryEntity.setIntervieweeName(dto.getIntervieweeName());
        bookingHistoryEntity.setIntervieweeMobileNo(dto.getIntervieweeMobileNo());
        bookingHistoryEntity.setIntervieweeResumeUrl(dto.getIntervieweeResumeUrl());
        bookingHistoryEntity.setStatus(BookingStatus.BOOKED);

        return bookingHistoryEntity;

    }

    public List<SlotBookingResponse> mapToSlotBookingResponseDTO(List<BookingHistoryEntity> bookingHistoryEntityList){
        return bookingHistoryEntityList.stream()
                .map(this::mapToSlotBookingResponseDTO)
                .collect(Collectors.toList());
    }

    public SlotBookingResponse mapToSlotBookingResponseDTO(BookingHistoryEntity bookingHistoryEntity){
        return SlotBookingResponse
                .builder()
                .bookingReference(bookingHistoryEntity.getBookingReference())
                .interviewDate(bookingHistoryEntity.getSlot().getInterviewDate())
                .build();
    }

    public BookingDetailsResponse mapToBookingDetailsResponseDTO(BookingHistoryEntity bookingHistoryEntity){
        return BookingDetailsResponse
                .builder()
                .bookingReference(bookingHistoryEntity.getBookingReference())
                .intervieweeEmail(bookingHistoryEntity.getIntervieweeEmail())
                .intervieweeMobileNo(bookingHistoryEntity.getIntervieweeMobileNo())
                .intervieweeResumeUrl(bookingHistoryEntity.getIntervieweeResumeUrl())
                .intervieweeName(bookingHistoryEntity.getIntervieweeName())
                .slotInfo(slotMapper.mapToSlotInfoResponseDTO(bookingHistoryEntity.getSlot()))
                .build();
    }
}
