package com.example.REST_API.Dto;

import com.example.REST_API.entity.Comment;
import com.example.REST_API.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String content;
    private String write_time;
    private String author_nickname;

    public CommentResponseDto(Comment comment){
        this.content = comment.getContent();
        this.write_time = comment.getWrite_time();
        this.author_nickname = comment.getAuthor().getNickname();
    }
}
