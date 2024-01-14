package com.ashtha.slotbooking.service;

import com.ashtha.slotbooking.helper.LocaleMessageHelper;
import com.ashtha.slotbooking.model.dto.email.EmailDto;
import com.ashtha.slotbooking.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class BaseService {

    protected final String RECORD_NOT_FOUND_MSG_KEY="api.response.NOT_FOUND.message";

    @Autowired
    protected LocaleMessageHelper messageHelper;
    @Autowired
    private EmailService emailService;

    protected String getLocaleMessage(String messageKey){
        return messageHelper.getLocalMessage(messageKey);
    }

    protected void sendEmail(EmailDto emailDto){
        try {
            emailService.send(emailDto)
                    .thenAcceptAsync(emilDto -> log.info("Mail sent Successful to {}", emilDto.getEmail()));
            log.info("Email sending in progress");
        } catch (InterruptedException e) {
            log.error("Exception occurred while sending email", e );
        }
    }

}
