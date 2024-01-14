package com.ashtha.slotbooking.model.request.slot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSlotRequest {
    @JsonProperty("interview_date")
    @NotNull(message = "validation.constraints.interviewDate.NotNull.message")
    @Future(message = "validation.constraints.interviewDate.future.message")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate interviewDate;

    @JsonProperty("start_time")
    @NotNull(message = "validation.constraints.startTime.NotNull.message")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @JsonProperty("end_time")
    @NotNull(message = "validation.constraints.endTime.NotNull.message")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
}
