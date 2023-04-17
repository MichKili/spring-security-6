package com.michal.amigoscode.security.user.service;

import com.michal.amigoscode.security.user.model.User;
import com.michal.amigoscode.security.user.model.dto.UserDTO;
import com.michal.amigoscode.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        User user = byEmail
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with this username: " + email));
        return new UserDTO(user);
    }

    @Override
    public UUID createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with this username: " + username));
    }
}
