package com.example.REST_API.Dto;

import com.example.REST_API.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank
    private String content;
    @NotBlank
    private User user;
}
