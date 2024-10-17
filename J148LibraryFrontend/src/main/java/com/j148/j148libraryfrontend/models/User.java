package com.j148.j148libraryfrontend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rossb
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private long userId;
    private String name, surname, username, password, email;
    private Role role;
}
enum Role{
    STANDARD,
    ADMIN
}
