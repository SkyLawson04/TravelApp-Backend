package com.travelapp.backend.user.service;


import com.travelapp.backend.user.dto.ChangePasswordRequest;
import com.travelapp.backend.user.dto.CreateNewPasswordRequest;
import com.travelapp.backend.user.dto.UserEditDTO;
import com.travelapp.backend.user.dto.UserRegisterDTO;
import com.travelapp.backend.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;
import java.util.List;

public interface UserService extends UserDetailsService {
    void changeEmail(String newEmail);

    void register (UserRegisterDTO dto);

    User getUserByUsername(String username);

    List<User> findAll();

    User getCurrentUser();

    void editUser(UserEditDTO dto) throws ParseException;

    void changePassword(ChangePasswordRequest changePasswordRequest);
}
