package com.ashtha.slotbooking.model.booking;

import com.ashtha.slotbooking.entity.SlotEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingValidationDto {
    private String intervieweeName;
    private String intervieweeMobileNo;
    private String intervieweeEmail;
    private String intervieweeResumeUrl;
    private SlotEntity slotEntity;
}
