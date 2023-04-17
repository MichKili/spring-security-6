package com.michal.amigoscode.security.auth.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthenticationRequest {
    String email;
    String password;

}
