package com.ashtha.slotbooking.controller;



import com.ashtha.slotbooking.model.request.booking.SlotBookingRequest;
import com.ashtha.slotbooking.model.response.ApiResponse;
import com.ashtha.slotbooking.model.response.booking.BookingDetailsResponse;
import com.ashtha.slotbooking.model.response.booking.SlotBookingResponse;
import com.ashtha.slotbooking.service.booking.BookingService;
import com.ashtha.slotbooking.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking-service")
public class BookingController extends BaseController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<ApiResponse<SlotBookingResponse>> bookSlot(@RequestBody @Valid SlotBookingRequest request,
                                                                       BindingResult result){
        super.throwIfHasError(result);
        return ResponseEntity.ok(ResponseBuilder.buildOkResponse(bookingService.bookSlot(request)));
    }

    @PutMapping("/book/{bookingReference}")
    public ResponseEntity<ApiResponse<SlotBookingResponse>> updateBooking(@PathVariable String bookingReference,
                                                                          @RequestBody @Valid SlotBookingRequest request,
                                                                          BindingResult result){
        super.throwIfHasError(result);
        return ResponseEntity.ok(ResponseBuilder.buildOkResponse(bookingService.updateBooking(bookingReference, request)));
    }

    @DeleteMapping("/book/{bookingReference}")
    public ResponseEntity<ApiResponse<Void>> cancelBooking(@PathVariable String bookingReference){
        bookingService.cancelBooking(bookingReference);
        return ResponseEntity.ok(ResponseBuilder.buildOkResponse());
    }

    @GetMapping("/book/all")
    public ResponseEntity<ApiResponse<List<SlotBookingResponse>>> getAllBookings(){
        return ResponseEntity.ok(ResponseBuilder.buildOkResponse(bookingService.getAllBookings()));
    }

    @GetMapping("/book/{bookingReference}")
    public ResponseEntity<ApiResponse<BookingDetailsResponse>> getBookingDetails(@PathVariable String bookingReference){
        return ResponseEntity.ok(ResponseBuilder.buildOkResponse(bookingService.getBookingDetails(bookingReference)));
    }

}
