package com.ashtha.slotbooking.service;

import com.ashtha.slotbooking.entity.SlotEntity;
import com.ashtha.slotbooking.enums.SlotStatus;
import com.ashtha.slotbooking.exception.RecordNotFoundException;
import com.ashtha.slotbooking.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotEntityService extends BaseCRUDService<SlotEntity, SlotRepository> {
    public SlotEntityService(SlotRepository repository) {
        super(repository);
    }

    public List<SlotEntity> getAllAvailableSlots() {
        return repository.findAllByStatus(SlotStatus.AVAILABLE);
    }

    public SlotEntity getAvailableSlotByTrackingCode(String trackingCode){
        return repository.findBySlotTrackingCodeAndStatus(trackingCode, SlotStatus.AVAILABLE)
                .orElseThrow(() -> new RecordNotFoundException("api.response.SLOT_NOT_AVAILABLE.message"));

    }
}
