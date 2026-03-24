package authorization.server.helpers;

@FunctionalInterface
public interface WiseShipEmailSanitizer {
    String sanitize(String email);
}

