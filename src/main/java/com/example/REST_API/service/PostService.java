package com.example.REST_API.service;

import com.example.REST_API.Dto.PostRequestDto;
import com.example.REST_API.Dto.PostResponseDto;
import com.example.REST_API.entity.Post;
import com.example.REST_API.entity.User;
import com.example.REST_API.exception.NotFoundException;
import com.example.REST_API.repository.PostRepository;
import com.example.REST_API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto createPost(Long userId, PostRequestDto request) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User_not_found"));

        Post post = new Post(
                request.getTitle(),
                request.getContent(),
                request.getImageUrl(),
                author
        );

        Post savedPost = postRepository.save(post);
        return new PostResponseDto(savedPost);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        // findAll로 모든 post 리스트 형태로 가져오기
        List<Post> posts = postRepository.findAll();

        // Dto를 담은 List를 반환해야 하므로
        List<PostResponseDto> result = new ArrayList<>();

        // 반복문을 통해 각 post 요소들을 dto 형태로 변환해 result에 추가
        for (Post post : posts) {
            PostResponseDto dto = new PostResponseDto(post);
            result.add(dto);
        }

        return result;
    }


    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        return new PostResponseDto(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        if (request.getTitle() != null) {
            post.changeTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            post.changeContent(request.getContent());
        }
        if (request.getImageUrl() != null) {
            post.changeImageFile(request.getImageUrl());
        }

        return new PostResponseDto(post);
    }
}
