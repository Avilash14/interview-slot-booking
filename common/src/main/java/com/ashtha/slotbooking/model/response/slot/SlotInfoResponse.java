package com.ashtha.slotbooking.model.response.slot;

import com.ashtha.slotbooking.enums.SlotStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SlotInfoResponse {
   private String slotTrackingCode;
   @JsonFormat(pattern = "yyyy-MM-dd")
   private LocalDate interviewDate;
   @JsonFormat(pattern = "HH:mm:ss")
   private LocalTime startTime;
   @JsonFormat(pattern = "HH:mm:ss")
   private LocalTime endTime;
   private SlotStatus slotStatus;
}
