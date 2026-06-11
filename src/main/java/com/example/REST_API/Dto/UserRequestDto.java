package com.example.REST_API.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자 생성
public class UserRequestDto {
    @Email      // 이메일 형식검사
    @NotBlank   // null, 공백 불허
    private String email;

    @NotBlank
    @Size(min=8)    // 최소 8글자
    private String password;

    @NotBlank
    private String nickname;

    // 선택사항 이므로 NotBlank 붙이지 않음
    private String profile_image;
}
