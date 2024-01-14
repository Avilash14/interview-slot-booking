package com.ashtha.slotbooking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    private String apiVersion = "1.0.0";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(getApiInfo());
    }

    private Info getApiInfo(){
        return new Info()
                .title("Astha Interview Slot Booking")
                .version(apiVersion)
                .description("A RESTful API Service for Interview Slot Booking Application")
                .contact(getContactInfo())
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }

    private Contact getContactInfo(){
        return new Contact()
                .name("Avilash Dutta")
                .email("avilashh14@gmail.com");
    }

}

