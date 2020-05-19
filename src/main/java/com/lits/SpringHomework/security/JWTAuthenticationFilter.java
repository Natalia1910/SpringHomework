package com.lits.SpringHomework.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lits.SpringHomework.dto.UserDto;
import com.lits.SpringHomework.model.Permission;
import com.lits.SpringHomework.model.Role;
import com.lits.SpringHomework.repository.UserRepository;
import com.lits.SpringHomework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.lits.SpringHomework.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    public JWTAuthenticationFilter(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        try {
            UserDto userDTO = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
            com.lits.SpringHomework.model.User user = userRepository.findByName(userDTO.getName()).orElseThrow();
            List<SimpleGrantedAuthority> permissions = user.getRole().stream().map(Role::getPermissions)
                    .flatMap(Collection::stream).map(Permission::getName).map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDTO.getName(),
                    userDTO.getPassword(),
                    permissions));
        } catch (Exception e) {

            if (e instanceof BadCredentialsException) {
                throw new com.lits.SpringHomework.exception.BadCredentialsException("Invalid login or password");
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) {
        User user = (User) auth.getPrincipal();
        UserDto userDTO = userService.findByUsername(user.getUsername());
        userDTO.setPassword(null);

        com.lits.SpringHomework.model.User userEntity = userRepository.findByName(userDTO.getName()).orElseThrow();
        List<String> permissions = userEntity.getRole().stream().map(Role::getPermissions).flatMap(Collection::stream)
                .map(Permission::getName).collect(Collectors.toList());

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim(USER_ID_PARAM, userDTO.getId())
                .withClaim(USER_ROLE_PARAM, permissions)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        response.setContentType("application/json");
    }
}
