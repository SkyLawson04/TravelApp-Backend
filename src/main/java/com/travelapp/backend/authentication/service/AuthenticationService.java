package com.travelapp.backend.authentication.service;

import com.travelapp.backend.authentication.dto.AuthenticationRequest;
import com.travelapp.backend.authentication.dto.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletRequest httpServletRequest);
}
