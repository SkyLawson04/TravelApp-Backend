package com.travelapp.backend.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document("z_user")
public class User {
    @Id
    private String id;

    private String email;

    @Indexed(background = true)
    private String username;
    private String password;
    private String phone;
    private String role;

    private String firstName;
    private String lastName;
}
