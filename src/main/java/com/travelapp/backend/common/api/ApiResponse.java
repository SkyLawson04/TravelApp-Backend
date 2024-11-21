package com.travelapp.backend.common.api;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private int code;
    private String message;
    private Object object;

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiResponse success(String message) {
        return new ApiResponse(HttpServletResponse.SC_OK, message);
    }

    public static ApiResponse success(String message, Object results) {
        return new ApiResponse(HttpServletResponse.SC_OK, message, results);
    }
}
