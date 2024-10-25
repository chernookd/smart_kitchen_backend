package org.example.smartkitchen.security.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.smartkitchen.domain.entity.user.UserEntity;
import org.example.smartkitchen.domain.repository.user.RoleRepository;
import org.example.smartkitchen.domain.repository.user.UserRepository;
import org.example.smartkitchen.dto.auth.AuthRequest;
import org.example.smartkitchen.dto.auth.AuthResponse;
import org.example.smartkitchen.security.services.jwt.JwtIssuer;
import org.example.smartkitchen.security.userPrincipal.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String ROLE_USER = "user";

    public AuthResponse register(AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        String email = authRequest.getEmail();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Same username");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setEmail(email);
        userEntity.setRole(roleRepository.findByName(ROLE_USER).orElseThrow());

        userRepository.save(userEntity);

        Authentication authentication = authenticateUser(username, password);

        return getAuthResponse(authentication);
    }

    public AuthResponse login(AuthRequest authRequest) {
        log.info("login");
        Authentication authentication = authenticateUser(authRequest.getUsername(), authRequest.getPassword());
        return getAuthResponse(authentication);
    }

    private Authentication authenticateUser(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private AuthResponse getAuthResponse(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(Object::toString)
                .toList();

        Long userId = userPrincipal.getUserId();
        String username = userPrincipal.getUsername();
        String email = userPrincipal.getEmail();

        String token = jwtIssuer.generateToken(userId, username, email, roles);

        return new AuthResponse(token);
    }
}
