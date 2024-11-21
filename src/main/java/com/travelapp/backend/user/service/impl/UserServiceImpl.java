package com.travelapp.backend.user.service.impl;

import com.travelapp.backend.common.exception.BusinessException;
import com.travelapp.backend.user.dto.ChangePasswordRequest;
import com.travelapp.backend.user.dto.UserEditDTO;
import com.travelapp.backend.user.dto.UserRegisterDTO;
import com.travelapp.backend.user.model.CustomUserDetails;
import com.travelapp.backend.user.model.User;
import com.travelapp.backend.user.repository.UserRepository;
import com.travelapp.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }

    @Override
    public void register(UserRegisterDTO dto) {
        String password = dto.getPassword();
        String email = dto.getEmail();
        String username = dto.getUsername();
        userRepository.findByUsername(dto.getUsername()).ifPresent(user -> {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "User already existed");
        });
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }


    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = getCurrentUser();
        if (!bCryptPasswordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw BusinessException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Wrong current password")
                    .build();
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(changePasswordRequest.getNewPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public void changeEmail(String newEmail) {
        User user = getCurrentUser();
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getCurrentUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return customUserDetails.getUser();
    }

    @Override
    public void editUser(UserEditDTO dto) throws ParseException {
        User user = getCurrentUser();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        userRepository.save(user);
    }

    private static String extractUserId(String token) {
        return StringUtils.substring(token, 0, StringUtils.indexOf(token, "-"));
    }
}