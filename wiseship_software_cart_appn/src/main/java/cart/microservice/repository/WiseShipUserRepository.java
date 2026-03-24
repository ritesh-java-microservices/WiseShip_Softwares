package cart.microservice.repository;


import cart.microservice.entities.WiseShipUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WiseShipUserRepository extends JpaRepository<WiseShipUserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<WiseShipUserEntity> findByEmail(String email);

    Page<WiseShipUserEntity> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String firstName, String lastName, String email, Pageable pageable
    );
}
