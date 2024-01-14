package com.ashtha.slotbooking.service.slot;


import com.ashtha.slotbooking.entity.SlotEntity;
import com.ashtha.slotbooking.enums.SlotStatus;
import com.ashtha.slotbooking.model.request.slot.CreateSlotRequest;
import com.ashtha.slotbooking.model.response.slot.SlotInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SlotMapper {

    public SlotEntity mapToEntity(CreateSlotRequest dto){
        SlotEntity slot = new SlotEntity();

        slot.setSlotTrackingCode(UUID.randomUUID().toString().replace("-",""));
        slot.setInterviewDate(dto.getInterviewDate());
        slot.setStartTime(dto.getStartTime());
        slot.setEndTime(dto.getEndTime());
        slot.setStatus(SlotStatus.AVAILABLE);
        return slot;

    }

    public List<SlotInfoResponse> mapToSlotInfoResponseDTO(List<SlotEntity> slotEntityList){
        return slotEntityList.stream()
                .map(this::mapToSlotInfoResponseDTO)
                .collect(Collectors.toList());
    }


    public SlotInfoResponse mapToSlotInfoResponseDTO(SlotEntity slotEntity){
        return SlotInfoResponse
                .builder()
                .slotTrackingCode(slotEntity.getSlotTrackingCode())
                .interviewDate(slotEntity.getInterviewDate())
                .startTime(slotEntity.getStartTime())
                .endTime(slotEntity.getEndTime())
                .slotStatus(slotEntity.getStatus())
                .build();
    }
}
