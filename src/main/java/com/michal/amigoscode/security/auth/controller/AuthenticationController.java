package com.michal.amigoscode.security.auth.controller;

import com.michal.amigoscode.security.auth.model.AuthenticationRequest;
import com.michal.amigoscode.security.auth.model.AuthenticationResponse;
import com.michal.amigoscode.security.auth.model.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/auth")
@Validated
public interface AuthenticationController {

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request);


    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request);

}
