package my.idp.spring.contract.security;

import my.idp.spring.contract.repository.TokenBlackListRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlackListRepository tokenBlackListRepository;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, TokenBlackListRepository tokenBlackListRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenBlackListRepository = tokenBlackListRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtTokenProvider.validateToken(token) && !tokenBlackListRepository.existsByToken(token)) {
                Authentication authentication = jwtTokenProvider.createAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
