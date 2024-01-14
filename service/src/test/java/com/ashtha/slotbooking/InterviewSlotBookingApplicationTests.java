package com.ashtha.slotbooking;

import com.ashtha.slotbooking.model.dto.email.EmailDto;
import com.ashtha.slotbooking.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class InterviewSlotBookingApplicationTests {
	@Autowired
	private EmailService emailService;

	@Test
	public void sendEmail() {
		try {
			emailService.send(EmailDto.builder()
					.email("test@gmail.com")
					.body("Mail sent for test")
					.build())
					.thenAcceptAsync(emilDto -> log.info("Mail sent Successful to {}", emilDto.getEmail()));
			log.info("Email sending in progress");
		} catch (InterruptedException e) {
			log.error("Exception occurred while sending email", e );
		}
	}

}
