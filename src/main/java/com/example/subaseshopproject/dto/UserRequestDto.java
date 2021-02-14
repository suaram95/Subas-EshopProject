package com.example.subaseshopproject.dto;

import com.example.subaseshopproject.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRequestDto {

    private long id;
    @NotBlank(message = "Field is required")
    private String firstName;
    @NotBlank(message = "Field is required")
    private String lastName;
    private String phone;
    @Email(message = "Email was not valid")
    private String email;
    @Size(min = 7, message = "Field is required")
    private String password;
    @Size(min = 7, message = "Field is required")
    private String confirmPassword;
    private Role role=Role.USER;

}
