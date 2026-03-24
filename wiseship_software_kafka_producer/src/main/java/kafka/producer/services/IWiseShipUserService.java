package kafka.producer.services;

import kafka.producer.request.WiseShipUserRequestDTO;
import kafka.producer.response.WiseShipUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IWiseShipUserService {
    WiseShipUserResponse create(WiseShipUserRequestDTO request);
    WiseShipUserResponse getById(Long id);
    Page<WiseShipUserResponse> list(String search, Pageable pageable);
    WiseShipUserResponse update(Long id, WiseShipUserRequestDTO request);
    WiseShipUserResponse patch(Long id, WiseShipUserRequestDTO request);
    void delete(Long id);
}
