package com.ashtha.slotbooking.service.booking;

import com.ashtha.slotbooking.entity.BookingHistoryEntity;
import com.ashtha.slotbooking.entity.SlotEntity;
import com.ashtha.slotbooking.enums.BookingStatus;
import com.ashtha.slotbooking.enums.SlotStatus;
import com.ashtha.slotbooking.exception.BadRequestException;
import com.ashtha.slotbooking.model.booking.BookingValidationDto;
import com.ashtha.slotbooking.model.booking.UpdateBookingValidationDto;
import com.ashtha.slotbooking.model.dto.email.EmailDto;
import com.ashtha.slotbooking.model.request.booking.SlotBookingRequest;
import com.ashtha.slotbooking.model.response.booking.BookingDetailsResponse;
import com.ashtha.slotbooking.model.response.booking.SlotBookingResponse;
import com.ashtha.slotbooking.service.BaseService;
import com.ashtha.slotbooking.service.BookingHistoryEntityService;
import com.ashtha.slotbooking.service.SlotEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl extends BaseService implements BookingService {

    private final SlotEntityService slotEntityService;
    private final BookingHistoryEntityService bookingHistoryEntityService;
    private final BookingMapper mapper;

    @Override
    public SlotBookingResponse bookSlot(SlotBookingRequest request) {
        BookingValidationDto validationDto = validateBookingRequest(request);

        BookingHistoryEntity bookingHistoryEntity = mapper.mapToEntity(validationDto);
        bookingHistoryEntityService.save(bookingHistoryEntity);

        updateSlotStatus(validationDto.getSlotEntity(), SlotStatus.BOOKED);

        sendEmail(EmailDto.builder()
                .email(validationDto.getIntervieweeEmail())
                .body("Slot Booked")
                .build()
        );

        return SlotBookingResponse.builder()
                .bookingReference(bookingHistoryEntity.getBookingReference())
                .interviewDate(bookingHistoryEntity.getSlot().getInterviewDate())
                .build();
    }

    @Override
    public SlotBookingResponse updateBooking(String bookingReference, SlotBookingRequest request) {
        UpdateBookingValidationDto validationDto = validateUpdateBookingRequest(bookingReference, request);

        SlotEntity currentSlotEntity = validationDto.getBookingHistoryEntity().getSlot();
        BookingHistoryEntity bookingHistoryEntity = mapper.mapToEntity(validationDto, validationDto.getBookingHistoryEntity());

        bookingHistoryEntityService.save(bookingHistoryEntity);

        updateSlotStatus(validationDto.getSlotEntity(), SlotStatus.BOOKED);

        updateSlotStatus(currentSlotEntity, SlotStatus.AVAILABLE);

        sendEmail(EmailDto.builder()
                .email(validationDto.getIntervieweeEmail())
                .body("Booking updated")
                .build()
        );

        return SlotBookingResponse.builder()
                .bookingReference(bookingHistoryEntity.getBookingReference())
                .interviewDate(bookingHistoryEntity.getSlot().getInterviewDate())
                .build();
    }

    @Override
    public void cancelBooking(String bookingReference) {
        BookingHistoryEntity bookingHistoryEntity = bookingHistoryEntityService.getBookingSlotData(bookingReference);

        if(LocalDate.now().isAfter(bookingHistoryEntity.getSlot().getInterviewDate()))
            throw new BadRequestException(getLocaleMessage("api.response.INVALID_CANCEL_REQUEST.message"));

        updateBookingStatus(bookingHistoryEntity, BookingStatus.CANCELLED);

        updateSlotStatus(bookingHistoryEntity.getSlot(), SlotStatus.AVAILABLE);

        sendEmail(EmailDto.builder()
                .email(bookingHistoryEntity.getIntervieweeEmail())
                .body("Booking canceled")
                .build()
        );

    }

    @Override
    public List<SlotBookingResponse> getAllBookings() {
        return mapper.mapToSlotBookingResponseDTO(bookingHistoryEntityService.findAll());
    }

    @Override
    public BookingDetailsResponse getBookingDetails(String bookingReference) {
        BookingHistoryEntity bookingHistoryEntity = bookingHistoryEntityService.getBookingSlotData(bookingReference);

        return mapper.mapToBookingDetailsResponseDTO(bookingHistoryEntity);

    }

    private BookingValidationDto validateBookingRequest(SlotBookingRequest request) {
        SlotEntity slotEntity = slotEntityService.getAvailableSlotByTrackingCode(request.getSlotTrackingCode());

        return BookingValidationDto.builder()
                .intervieweeName(request.getIntervieweeName())
                .intervieweeEmail(request.getIntervieweeEmail())
                .intervieweeMobileNo(request.getIntervieweeMobileNo())
                .intervieweeResumeUrl(request.getIntervieweeResumeUrl())
                .slotEntity(slotEntity)
                .build();
    }

    private void updateSlotStatus(SlotEntity slotEntity, SlotStatus slotStatus) {
        slotEntity.setStatus(slotStatus);
        slotEntityService.save(slotEntity);
    }

    private void updateBookingStatus(BookingHistoryEntity bookingHistoryEntity, BookingStatus bookingStatus) {
        bookingHistoryEntity.setStatus(bookingStatus);
        bookingHistoryEntityService.save(bookingHistoryEntity);
    }


    private UpdateBookingValidationDto validateUpdateBookingRequest(String bookingReference, SlotBookingRequest request) {

        SlotEntity slotEntity = slotEntityService.getAvailableSlotByTrackingCode(request.getSlotTrackingCode());

        BookingHistoryEntity bookingHistoryEntity = bookingHistoryEntityService.getBookingSlotData(bookingReference);

        return UpdateBookingValidationDto
                .builder()
                .bookingHistoryEntity(bookingHistoryEntity)
                .intervieweeEmail(request.getIntervieweeEmail())
                .intervieweeMobileNo(request.getIntervieweeMobileNo())
                .intervieweeName(request.getIntervieweeName())
                .intervieweeResumeUrl(request.getIntervieweeResumeUrl())
                .slotEntity(slotEntity)
                .build();
    }
}
