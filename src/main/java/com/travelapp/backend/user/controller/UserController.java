package com.travelapp.backend.user.controller;

import com.travelapp.backend.common.api.ApiResponse;
import com.travelapp.backend.user.dto.ChangePasswordRequest;
import com.travelapp.backend.user.dto.UserEditDTO;
import com.travelapp.backend.user.model.User;
import com.travelapp.backend.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User")
@RequestMapping("/api/customer/v1")
public class UserController {
    private final UserService userService;

    @PutMapping("/change_password")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            userService.changePassword(request);
            return ResponseEntity.ok(ApiResponse.success("Change password success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @PutMapping("/email/change")
    public ResponseEntity<ApiResponse> changeEmail(@RequestBody String newEmail) {
        try {
            userService.changeEmail(newEmail);
            return ResponseEntity.ok(ApiResponse.success("Change email success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<ApiResponse> editUser(@RequestBody UserEditDTO dto) {
        try {
            System.out.println("UwU");
            userService.editUser(dto);
            return ResponseEntity.ok(ApiResponse.success("Edit user successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> editUser() {
        try {
            User user = userService.getCurrentUser();
            return ResponseEntity.ok(ApiResponse.success("Get user successfully", user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }
    }
}
