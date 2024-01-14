package com.ashtha.slotbooking.controller;



import com.ashtha.slotbooking.model.request.slot.CreateSlotRequest;
import com.ashtha.slotbooking.model.response.ApiResponse;
import com.ashtha.slotbooking.model.response.slot.SlotInfoResponse;
import com.ashtha.slotbooking.service.slot.SlotService;
import com.ashtha.slotbooking.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking-service")
public class SlotController extends BaseController {

    private final SlotService slotService;

    @PostMapping("/slots")
    public ResponseEntity<ApiResponse<SlotInfoResponse>> createSlot(@RequestBody @Valid CreateSlotRequest request,
                                                                    BindingResult result){
        super.throwIfHasError(result);
        return ResponseEntity.ok(ResponseBuilder.buildOkResponse(slotService.createSlot(request)));
    }

    @GetMapping("/slots")
    public ResponseEntity<ApiResponse<List<SlotInfoResponse>>> getAllSlots(){
        return ResponseEntity.ok(ResponseBuilder.buildOkResponse(slotService.getAllSlots()));
    }

}
