package com.michal.amigoscode.security.auth.service;

import com.michal.amigoscode.security.auth.model.AuthenticationRequest;
import com.michal.amigoscode.security.auth.model.AuthenticationResponse;
import com.michal.amigoscode.security.auth.model.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
