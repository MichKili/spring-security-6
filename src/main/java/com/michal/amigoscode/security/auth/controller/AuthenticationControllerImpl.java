package com.michal.amigoscode.security.auth.controller;

import com.michal.amigoscode.security.auth.model.AuthenticationRequest;
import com.michal.amigoscode.security.auth.model.AuthenticationResponse;
import com.michal.amigoscode.security.auth.service.AuthenticationService;
import com.michal.amigoscode.security.auth.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {


    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
