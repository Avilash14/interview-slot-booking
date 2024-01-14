package com.ashtha.slotbooking.model.request.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotBookingRequest {
    @JsonProperty("interviewee_name")
    @NotBlank(message = "validation.constraints.intervieweeName.NotNull.message")
    private String intervieweeName;

    @JsonProperty("interviewee_mobile_no")
    @NotBlank(message = "validation.constraints.intervieweeMobileNo.NotNull.message")
    private String intervieweeMobileNo;

    @JsonProperty("interviewee_email")
    @NotBlank(message = "validation.constraints.intervieweeEmail.NotNull.message")
    private String intervieweeEmail;

    @JsonProperty("interviewee_resume_url")
    @NotBlank(message = "validation.constraints.intervieweeResumeUrl.NotNull.message")
    private String intervieweeResumeUrl;

    @JsonProperty("slot_tracking_code")
    @NotBlank(message = "validation.constraints.slotTrackingCode.NotNull.message")
    private String slotTrackingCode;

}
