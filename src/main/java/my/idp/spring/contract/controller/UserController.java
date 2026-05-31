package my.idp.spring.contract.controller;

import lombok.RequiredArgsConstructor;
import my.idp.spring.contract.dto.UserRequestDto;
import my.idp.spring.contract.dto.UserResponseDto;
import my.idp.spring.contract.entity.Role;
import my.idp.spring.contract.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final my.idp.spring.contract.mapper.UserMapper userMapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> users = userService.getAll().stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        return userService.getById(id)
                .map(userMapper::mapToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userMapper.mapToDto(userService.createUser(userRequestDto)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/role/{role}")
    public ResponseEntity<Void> addRoleForUser(@PathVariable Long id, @PathVariable Role role) {
        userService.addRoleForUser(id, role);
        return ResponseEntity.ok().build();
    }
}
