package com.ashtha.slotbooking.service.slot;


import com.ashtha.slotbooking.entity.SlotEntity;
import com.ashtha.slotbooking.exception.BadRequestException;
import com.ashtha.slotbooking.exception.RecordNotFoundException;
import com.ashtha.slotbooking.model.request.slot.CreateSlotRequest;
import com.ashtha.slotbooking.model.response.slot.SlotInfoResponse;
import com.ashtha.slotbooking.service.BaseService;
import com.ashtha.slotbooking.service.SlotEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlotServiceImpl extends BaseService implements SlotService {

    private final SlotEntityService slotEntityService;
    private final SlotMapper slotMapper;

    @Override
    public SlotInfoResponse createSlot(CreateSlotRequest request) {
        if(request.getEndTime().isBefore(request.getStartTime()))
            throw new BadRequestException(getLocaleMessage("validation.constraints.invalid.slot.time.message"));

        SlotEntity entity = slotMapper.mapToEntity(request);
        slotEntityService.save(entity);
        return slotMapper.mapToSlotInfoResponseDTO(entity);
    }

    @Override
    public List<SlotInfoResponse> getAllSlots() {
        List<SlotEntity> slotEntityList = slotEntityService.findAll()
                .stream()
                .filter(slot -> slot.getInterviewDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());

        if(CollectionUtils.isEmpty(slotEntityList))
            throw new RecordNotFoundException(messageHelper.getLocalMessage(RECORD_NOT_FOUND_MSG_KEY));

        return slotMapper.mapToSlotInfoResponseDTO(slotEntityList);
    }

}
