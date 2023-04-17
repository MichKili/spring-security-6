package com.michal.amigoscode.security.user.service;

import com.michal.amigoscode.security.user.model.User;
import com.michal.amigoscode.security.user.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    UserDTO getUserByEmail(String email);

    UUID createUser(User user);
}
