package files.documents.helpers;

@FunctionalInterface
public interface WiseShipEmailSanitizer {
    String sanitize(String email);
}

