package com.example.REST_API.service;

import com.example.REST_API.Dto.PostRequestDto;
import com.example.REST_API.Dto.PostResponseDto;
import com.example.REST_API.Dto.UserRequestDto;
import com.example.REST_API.Dto.UserResponseDto;
import com.example.REST_API.entity.Post;
import com.example.REST_API.repository.PostRepository;
import com.example.REST_API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor    // final과 NonNull이 붙은 변수를 인자들에 대한 생성자 자동 생성 (여기선 userRepository)
public class PostService {
    private final PostRepository postRepository;

    // 생성
    public PostResponseDto createPost(PostRequestDto request){
        Post post = postRepository.save(request.getTitle(), request.getContent(), request.getImage_url(), request.getUser());
        return new PostResponseDto(post);
    }

    // 조회
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new IllegalArgumentException("Post not found");
        }
        post.visited_count_up();
        // likeCount
        // commentCount
        return new PostResponseDto(post);
    }

    // 삭제
    public void deletePost(Long id){
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new IllegalArgumentException("Post not found");
        }
        postRepository.deleteById(id);
    }

    // 수정
    public PostResponseDto updatePost(Long id, PostRequestDto request){
        Post post = postRepository.findById(id);

        // 이렇게 if문이 계속 생기면 안좋은 코드같다는 생각이
        // 그래서 PUT 이 아니라 Patch를 사용해서 Patch용 Dto를 사용하라는 건가?
        if (post == null) {
            throw new IllegalArgumentException("Post not found");
        }
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
