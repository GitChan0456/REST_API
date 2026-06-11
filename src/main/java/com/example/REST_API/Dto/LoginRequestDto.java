package com.example.REST_API.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
