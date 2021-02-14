package com.example.subaseshopproject.dto;

import com.example.subaseshopproject.model.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    private long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String phone;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    private Role role=Role.USER;

}
