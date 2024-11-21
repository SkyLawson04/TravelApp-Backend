package com.travelapp.backend.authentication.controller;

import com.travelapp.backend.authentication.dto.AuthenticationRequest;
import com.travelapp.backend.authentication.service.AuthenticationService;
import com.travelapp.backend.common.api.ApiResponse;
import com.travelapp.backend.user.dto.UserRegisterDTO;
import com.travelapp.backend.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication")
@RequestMapping("/api/v1")
public class UserAuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authenticate (@RequestBody AuthenticationRequest request, HttpServletRequest httpRequest) {
        try {
            return ResponseEntity.ok(ApiResponse.success("Authentication successful", authenticationService.authenticate(request, httpRequest)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register (@RequestBody UserRegisterDTO request) {
        try {
            userService.register(request);
            return ResponseEntity.ok(ApiResponse.success("Registration success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }
}

