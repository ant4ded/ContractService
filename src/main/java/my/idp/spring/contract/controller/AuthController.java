package my.idp.spring.contract.controller;

import my.idp.spring.contract.entity.TokenBlackList;
import my.idp.spring.contract.security.AuthRequestDto;
import my.idp.spring.contract.security.AuthResponseDto;
import my.idp.spring.contract.security.JwtTokenProvider;
import my.idp.spring.contract.security.UserDetailsServiceImpl;
import my.idp.spring.contract.repository.TokenBlackListRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenBlackListRepository tokenBlackListRepository;

    public AuthController(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService, 
                          BCryptPasswordEncoder passwordEncoder, TokenBlackListRepository tokenBlackListRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.tokenBlackListRepository = tokenBlackListRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtTokenProvider.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            tokenBlackListRepository.save(new TokenBlackList(token));
        }
        return ResponseEntity.noContent().build();
    }
}
