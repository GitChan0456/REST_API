package com.example.REST_API.Dto;

import com.example.REST_API.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자 생성
public class UserResponseDto {
    private Long user_id;
    private String email;
    private String nickname;
    private String profile_image;

    public UserResponseDto(User user) {
        this.user_id = user.getUser_id();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profile_image = user.getProfile_image();
    }
}
