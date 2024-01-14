package com.ashtha.slotbooking.entity;

import com.ashtha.slotbooking.entity.base.BaseEntity;
import com.ashtha.slotbooking.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "BOOKING_HISTORY")
public class BookingHistoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BOOKING_REFERENCE", nullable = false, unique = true)
    private String bookingReference;

    @Column(name = "INTERVIEWEE_NAME", nullable = false)
    private String intervieweeName;

    @Column(name = "INTERVIEWEE_MOBILE_NO", nullable = false)
    private String intervieweeMobileNo;

    @Column(name = "INTERVIEWEE_EMAIL", nullable = false)
    private String intervieweeEmail;

    @Column(name = "INTERVIEWEE_RESUME_URL", nullable = false)
    private String intervieweeResumeUrl;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "SLOT_ID")
    @JsonIgnore
    private SlotEntity slot;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

}
