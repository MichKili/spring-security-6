package com.michal.amigoscode.security.config;

import com.michal.amigoscode.security.auth.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()//here we disable token verification because it slow and  consuming resources
                .authorizeHttpRequests() //here we set that now it will be part of authorization request when we will
                // define whitelist ( this is list of endpoint which doesnt require authentication like logging or register user
                .requestMatchers("api/v1/auth/**")//pattern for whitelist of request which should be always available without authentication
                .permitAll() //we permit all upper request
                .anyRequest() //and rest of all request
                .authenticated() //should be authenticated through filters and so on
                .and()
                .sessionManagement() //here we set properties of session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //we want to authenticate all request so we
                                                                        // dont need session so set STATELESS
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                // we want that our filter we created should be added before this main filter
        return httpSecurity.build();
    }

}
