package com.ashtha.slotbooking.service.slot;

import com.ashtha.slotbooking.model.request.slot.CreateSlotRequest;
import com.ashtha.slotbooking.model.response.slot.SlotInfoResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SlotService {

    @Transactional
    SlotInfoResponse createSlot(CreateSlotRequest request);

    @Transactional(readOnly = true)
    List<SlotInfoResponse> getAllSlots();
}
