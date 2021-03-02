package com.hunganh.mymoment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
}
