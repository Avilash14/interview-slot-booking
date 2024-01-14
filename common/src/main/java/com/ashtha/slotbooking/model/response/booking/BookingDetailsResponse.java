package com.ashtha.slotbooking.model.response.booking;

import com.ashtha.slotbooking.model.response.slot.SlotInfoResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingDetailsResponse {
    private String bookingReference;
    private String intervieweeName;
    private String intervieweeMobileNo;
    private String intervieweeEmail;
    private String intervieweeResumeUrl;
    private SlotInfoResponse slotInfo;
}
