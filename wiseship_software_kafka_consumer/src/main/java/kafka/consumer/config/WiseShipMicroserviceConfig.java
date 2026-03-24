package kafka.consumer.config;

import kafka.consumer.helpers.WiseShipEmailSanitizer;
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
