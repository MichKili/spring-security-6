package com.michal.amigoscode.security.auth.service;

import com.michal.amigoscode.security.auth.model.AuthenticationRequest;
import com.michal.amigoscode.security.auth.model.AuthenticationResponse;
import com.michal.amigoscode.security.auth.model.RegisterRequest;
import com.michal.amigoscode.security.user.model.Role;
import com.michal.amigoscode.security.user.model.User;
import com.michal.amigoscode.security.user.model.dto.UserDTO;
import com.michal.amigoscode.security.user.repository.UserRepository;
import com.michal.amigoscode.security.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(Role.USER))
                .isEnabled(true)
                .isAccountNotExpired(true)
                .isCredentialsNonExpired(true)
                .isAccountNotLocked(true)
                .build();
        userService.createUser(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDTO userByEmail = userService.getUserByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(userByEmail);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
