package com.ashtha.slotbooking.repository;

import com.ashtha.slotbooking.entity.SlotEntity;
import com.ashtha.slotbooking.enums.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface SlotRepository extends JpaRepository<SlotEntity, Long>, JpaSpecificationExecutor<SlotEntity> {

    List<SlotEntity> findAllByStatus(SlotStatus status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SlotEntity> findBySlotTrackingCodeAndStatus(String slotTrackingCode, SlotStatus status);

}
