package api.gateway.config;

import api.gateway.helpers.WiseShipEmailSanitizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WiseShipMicroserviceConfig {

    // A simple custom bean to demonstrate Spring Beans
    @Bean
    public WiseShipEmailSanitizer emailSanitizer() {
        return email -> email == null ? null : email.trim().toLowerCase();
    }
}
