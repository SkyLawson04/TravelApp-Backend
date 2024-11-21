package com.travelapp.backend.authentication.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.backend.common.api.ApiResponse;
import com.travelapp.backend.common.exception.AuthorizationException;
import com.travelapp.backend.user.model.CustomUserDetails;
import com.travelapp.backend.user.service.UserService;
import com.travelapp.backend.util.JwtUtilsUser;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(JwtConstant.JWT_HEADER);

        if (header != null && header.startsWith(JwtConstant.JWT_PREFIX)) {
            String token = header.substring(JwtConstant.JWT_PREFIX.length());
            String username;

            try {
                username = JwtUtilsUser.extractJwtUsername(token);
            } catch (JwtException e) {
                AuthorizationException exception = new AuthorizationException(e.getMessage());
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write(mapper.writeValueAsString(new ApiResponse(exception.getStatus().value(), exception.getMessage())));
                return;
            }

            CustomUserDetails customUserDetails;

            try {
                customUserDetails = (CustomUserDetails) userService.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                AuthorizationException exception = new AuthorizationException("User not found");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write(mapper.writeValueAsString(new ApiResponse(exception.getStatus().value(), exception.getMessage())));
                return;
            }

            if (JwtUtilsUser.validateJwtToken(token, customUserDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}


