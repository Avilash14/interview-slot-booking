package com.ashtha.slotbooking.model.response.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SlotBookingResponse {
    private String bookingReference;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate interviewDate;
}
