package com.example.REST_API.controller;

import com.example.REST_API.Dto.CommentRequestDto;
import com.example.REST_API.Dto.CommentResponseDto;
import com.example.REST_API.Dto.PostRequestDto;
import com.example.REST_API.Dto.PostResponseDto;
import com.example.REST_API.repository.CommentRepository;
import com.example.REST_API.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController                     // JSON으로 응답 반환
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor           // NonNull 또는 final이 붙은 필드에 대한 생성자
public class CommentController {
    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommentResponseDto createComment(@PathVariable Long postId, @Valid @RequestBody CommentRequestDto request, HttpSession session) {
        Long loginUserId = (Long) session.getAttribute("LOGIN_USER_ID");

        if (loginUserId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        return commentService.createComment(loginUserId, postId, request);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequestDto request){
        return commentService.updateComment(commentId, request);
    }

    @GetMapping("/{commentId}")
    public CommentResponseDto getComment(@PathVariable Long commentId){
        return commentService.getComment(commentId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }

}
