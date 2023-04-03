package com.example.segomarketnew.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String name;
    @Pattern(regexp ="^[A-Za-z\\d.,:;_\\-?!+=/'\\\\\"*\\[\\]{}()]{8,100}$", message = "Password not valid.")
    private String password;
    @Email(message = "Email not valid")
    private String email;
}
