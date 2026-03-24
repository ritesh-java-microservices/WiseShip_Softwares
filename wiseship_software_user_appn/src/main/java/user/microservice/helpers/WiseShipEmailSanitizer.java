package user.microservice.helpers;

@FunctionalInterface
public interface WiseShipEmailSanitizer {
    String sanitize(String email);
}

