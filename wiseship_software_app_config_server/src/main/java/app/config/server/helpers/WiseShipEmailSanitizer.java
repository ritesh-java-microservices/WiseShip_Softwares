package app.config.server.helpers;

@FunctionalInterface
public interface WiseShipEmailSanitizer {
    String sanitize(String email);
}

