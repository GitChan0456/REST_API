package com.example.REST_API.service;

import com.example.REST_API.Dto.PostRequestDto;
import com.example.REST_API.Dto.PostResponseDto;
import com.example.REST_API.Dto.UserRequestDto;
import com.example.REST_API.Dto.UserResponseDto;
import com.example.REST_API.entity.Post;
import com.example.REST_API.entity.User;
import com.example.REST_API.repository.PostRepository;
import com.example.REST_API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor    // final과 NonNull이 붙은 변수를 인자들에 대한 생성자 자동 생성 (여기선 userRepository)
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 생성
    @Transactional
    public PostResponseDto createPost(Long userId, PostRequestDto request) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = new Post(
                request.getTitle(),
                request.getContent(),
                request.getImage_url(),
                author
        );

        Post savedPost = postRepository.save(post);
        return new PostResponseDto(savedPost);
    }

    // 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // 조회수 증가 추후 적용
        return new PostResponseDto(post);
    }

    // 삭제
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto request){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // 이렇게 if문이 계속 생기면 안좋은 코드같다는 생각이
        // 그래서 PUT 이 아니라 Patch를 사용해서 Patch용 Dto를 사용하라는 건가?
        if (request.getTitle() != null){
            post.changeTitle(request.getTitle());
        }
        if (request.getContent() != null){
            post.changeContent(request.getContent());
        }
        if (request.getImage_url() != null){
            post.changeImageFile(request.getImage_url());
        }

        return new PostResponseDto(post);
    }


}
