package com.ashtha.slotbooking.service.email;

import java.util.concurrent.CompletableFuture;

import com.ashtha.slotbooking.model.dto.email.EmailDto;

public interface EmailService {
    CompletableFuture<EmailDto> send(EmailDto emailDto) throws InterruptedException;
}
