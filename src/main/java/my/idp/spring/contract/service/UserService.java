package my.idp.spring.contract.service;

import lombok.RequiredArgsConstructor;
import my.idp.spring.contract.dto.UserRequestDto;
import my.idp.spring.contract.entity.Role;
import my.idp.spring.contract.entity.User;
import my.idp.spring.contract.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User createUser(UserRequestDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(dto.getRoles())
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public void addRoleForUser(Long id, Role role) {
        userRepository.findById(id).ifPresent(user -> user.getRoles().add(role));
    }
}
