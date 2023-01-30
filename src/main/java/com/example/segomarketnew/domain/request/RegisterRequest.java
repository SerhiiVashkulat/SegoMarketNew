package com.example.segomarketnew.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Name must not be empty")
    @Pattern(regexp ="^[A-Za-z\\d._-]{4,15}$", message = "Nick name not valid.")
    private String name;
    @NotBlank(message = "Password must not be empty")
    @Pattern(regexp ="^[A-Za-z\\d.,:;_\\-?!+=/'\\\\\"*\\[\\]{}()]{8,100}$", message = "Password not valid.")
    private String password;
    @Email(message = "Email not valid")
    @NotBlank(message = "Email must not be empty")
    private String email;
    @NotBlank(message = "Matching password must not be empty")
    @Pattern(regexp ="^[A-Za-z\\d.,:;_\\-?!+=/'\\\\\"*\\[\\]{}()]{8,100}$", message = "Password not valid.")
    private String matchingPassword;


}
