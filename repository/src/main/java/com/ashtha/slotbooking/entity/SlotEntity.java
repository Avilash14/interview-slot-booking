package com.ashtha.slotbooking.entity;

import com.ashtha.slotbooking.entity.base.BaseEntity;
import com.ashtha.slotbooking.enums.SlotStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "SLOT")
public class SlotEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SLOT_TRACKING_CODE", unique = true, nullable = false)
    private String slotTrackingCode;

    @Column(name = "INTERVIEW_DATE", columnDefinition = "DATE")
    private LocalDate interviewDate;

    @Column(name = "START_TIME", columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "END_TIME", columnDefinition = "TIME")
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SlotStatus status;
}
