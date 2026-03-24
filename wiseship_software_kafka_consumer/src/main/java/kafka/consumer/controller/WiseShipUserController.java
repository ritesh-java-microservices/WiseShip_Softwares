package kafka.consumer.controller;

import kafka.consumer.request.WiseShipUserRequestDTO;
import kafka.consumer.response.WiseShipUserResponse;
import kafka.consumer.services.IWiseShipUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class WiseShipUserController {

    private final IWiseShipUserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WiseShipUserResponse create(@Valid @RequestBody WiseShipUserRequestDTO request) {
        return userService.create(request);
    }

    @GetMapping("/{id}")
    public WiseShipUserResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public Page<WiseShipUserResponse> list(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "lastName") Pageable pageable
    ) {
        return userService.list(search, pageable);
    }

    @PutMapping("/{id}")
    public WiseShipUserResponse update(@PathVariable Long id, @Valid @RequestBody WiseShipUserRequestDTO request) {
        return userService.update(id, request);
    }

    @PatchMapping("/{id}")
    public WiseShipUserResponse patch(@PathVariable Long id, @RequestBody WiseShipUserRequestDTO request) {
        // Partial update (validation handled in service)
        return userService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
