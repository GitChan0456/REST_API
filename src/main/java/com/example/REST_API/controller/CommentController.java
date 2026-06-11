package com.example.REST_API.controller;

import com.example.REST_API.Dto.CommentRequestDto;
import com.example.REST_API.Dto.CommentResponseDto;
import com.example.REST_API.Dto.PostRequestDto;
import com.example.REST_API.Dto.PostResponseDto;
import com.example.REST_API.repository.CommentRepository;
import com.example.REST_API.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController                     // JSON으로 응답 반환
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor           // NonNull 또는 final이 붙은 필드에 대한 생성자
public class CommentController {
    private final CommentService commentservice;

    @PostMapping
    public CommentResponseDto createComment(@Valid @RequestBody CommentRequestDto request){
        return commentservice.createComment(request);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequestDto request){
        return commentservice.updateComment(commentId, request);
    }

    @GetMapping("/{commentId}")
    public CommentResponseDto getComment(@PathVariable Long commentId){
        return commentservice.getComment(commentId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentservice.deleteComment(commentId);
    }

}
