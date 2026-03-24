package payment.microservice.services.impl;

import payment.microservice.entities.WiseShipUserEntity;
import payment.microservice.exception.DuplicateEmailException;
import payment.microservice.exception.ResourceNotFoundException;
import payment.microservice.helpers.WiseShipEmailSanitizer;
import payment.microservice.repository.WiseShipUserRepository;
import payment.microservice.request.WiseShipUserRequestDTO;
import payment.microservice.response.WiseShipUserResponse;
import payment.microservice.services.IWiseShipUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class WiseShipUserServiceImpl implements IWiseShipUserService {

    private final WiseShipUserRepository userRepository;
    private final WiseShipEmailSanitizer emailSanitizer; // custom Spring bean

    @Override
    public WiseShipUserResponse create(WiseShipUserRequestDTO request) {
        String email = emailSanitizer.sanitize(request.email());
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already in use: " + email);
        }
        WiseShipUserEntity rightDigitsUserEntity = WiseShipUserEntity.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(email)
                .phone(request.phone())
                .build();
        return toResponse(userRepository.save(rightDigitsUserEntity));
    }

    @Override
    public WiseShipUserResponse getById(Long id) {
        WiseShipUserEntity rightDigitsUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WiseShipUser not found: id=" + id));
        return toResponse(rightDigitsUserEntity);
    }

    @Override
    public Page<WiseShipUserResponse> list(String search, Pageable pageable) {
        if (search == null || search.isBlank()) {
            return userRepository.findAll(pageable).map(this::toResponse);
        }
        return userRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        search, search, search, pageable
                ).map(this::toResponse);
    }

    @Override
    public WiseShipUserResponse update(Long id, WiseShipUserRequestDTO request) {
        WiseShipUserEntity rightDigitsUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WiseShipUser not found: id=" + id));

        String newEmail = emailSanitizer.sanitize(request.email());
        if (!rightDigitsUserEntity.getEmail().equalsIgnoreCase(newEmail) && userRepository.existsByEmail(newEmail)) {
            throw new DuplicateEmailException("Email already in use: " + newEmail);
        }

        rightDigitsUserEntity.setFirstName(request.firstName());
        rightDigitsUserEntity.setLastName(request.lastName());
        rightDigitsUserEntity.setEmail(newEmail);
        rightDigitsUserEntity.setPhone(request.phone());

        return toResponse(userRepository.save(rightDigitsUserEntity));
    }

    @Override
    public WiseShipUserResponse patch(Long id, WiseShipUserRequestDTO request) {
        WiseShipUserEntity rightDigitsUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WiseShipUser not found: id=" + id));

        if (request.firstName() != null && !request.firstName().isBlank()) {
            rightDigitsUserEntity.setFirstName(request.firstName());
        }
        if (request.lastName() != null && !request.lastName().isBlank()) {
            rightDigitsUserEntity.setLastName(request.lastName());
        }
        if (request.email() != null && !request.email().isBlank()) {
            String newEmail = emailSanitizer.sanitize(request.email());
            if (!rightDigitsUserEntity.getEmail().equalsIgnoreCase(newEmail) && userRepository.existsByEmail(newEmail)) {
                throw new DuplicateEmailException("Email already in use: " + newEmail);
            }
            rightDigitsUserEntity.setEmail(newEmail);
        }
        if (request.phone() != null) {
            rightDigitsUserEntity.setPhone(request.phone());
        }
        return toResponse(userRepository.save(rightDigitsUserEntity));
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("WiseShipUser not found: id=" + id);
        }
        userRepository.deleteById(id);
    }

    private WiseShipUserResponse toResponse(WiseShipUserEntity u) {
        return new WiseShipUserResponse(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getEmail(),
                u.getPhone(),
                u.getCreatedAt(),
                u.getUpdatedAt()
        );
    }
}
