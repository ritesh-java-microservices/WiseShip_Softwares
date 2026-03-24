package client.management.helpers;

@FunctionalInterface
public interface WiseShipEmailSanitizer {
    String sanitize(String email);
}

