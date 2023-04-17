package com.michal.amigoscode.security.user.model.dto;

import com.michal.amigoscode.security.user.model.Role;
import com.michal.amigoscode.security.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@Builder
@AllArgsConstructor
public class UserDTO implements UserDetails {
    String firstName;
    String lastName;
    String email;
    String password;
    boolean isAccountNotExpired;
    boolean isAccountNotLocked;
    boolean isCredentialsNonExpired;
    boolean isEnabled;

    Set<Role> roles;

    public UserDTO(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        password = user.getPassword();
        isAccountNotExpired = user.isAccountNotExpired();
        isAccountNotLocked = user.isAccountNotLocked();
        isCredentialsNonExpired = user.isCredentialsNonExpired();
        isEnabled = user.isEnabled();
        roles = user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(e -> new SimpleGrantedAuthority(e.name())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
