package com.example.REST_API.Dto;

import com.example.REST_API.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 인자 없는 생성자 생성
public class PostRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private User user;
    // 이미지는 선택사항
    private String image_url;

}
