package com.travelapp.backend.authentication.service.impl;

import com.travelapp.backend.authentication.dto.AuthenticationRequest;
import com.travelapp.backend.authentication.dto.AuthenticationResponse;
import com.travelapp.backend.authentication.service.AuthenticationService;
import com.travelapp.backend.common.exception.UserNotFoundException;
import com.travelapp.backend.common.exception.WrongPasswordException;
import com.travelapp.backend.user.model.User;
import com.travelapp.backend.user.service.UserService;
import com.travelapp.backend.util.JwtUtilsUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;

    private final MongoTemplate mongoTemplate;

    private final AuthenticationManager authenticationManager;

    private final WrongPasswordException wrongPasswordException = new WrongPasswordException("Wrong password");

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletRequest httpServletRequest) {
        User user = userService.getUserByUsername(request.getUsername());
        if (user == null) {
            throw new UserNotFoundException(request.getUsername());
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw wrongPasswordException;
        }
        String jwt = JwtUtilsUser.generateJwtToken(user.getUsername());
        return new AuthenticationResponse(jwt);
    }
}
