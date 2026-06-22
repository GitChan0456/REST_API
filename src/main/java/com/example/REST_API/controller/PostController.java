package com.example.REST_API.controller;

import com.example.REST_API.Dto.PostRequestDto;
import com.example.REST_API.Dto.PostResponseDto;
import com.example.REST_API.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController                     // JSON으로 응답 반환
@RequestMapping("/posts")        // /posts 경로로 오는 요청들은 이 컨트롤러로 들어옴
@RequiredArgsConstructor           // NonNull 또는 final이 붙은 필드에 대한 생성자
public class PostController {
    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PostResponseDto createPost(
            @Valid @RequestBody PostRequestDto request,
            HttpSession session
    ) {
        Long loginUserId = (Long) session.getAttribute("LOGIN_USER_ID");

        if (loginUserId == null) {
            throw new IllegalArgumentException(
                    "로그인이 필요합니다."
            );
        }

        return postService.createPost(loginUserId, request);
    }
    /*
    @PostMapping
    public PostResponseDto createPost(@Valid @RequestBody PostRequestDto request){
        return postService.createPost(request);
    }
     */

    @GetMapping("/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    @PutMapping("/{postId}")
    public PostResponseDto UpdatePost(@PathVariable Long postId, @RequestBody PostRequestDto request){
        // Valid를 의도적으로 붙이지 않음
        return postService.updatePost(postId, request);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }


}
