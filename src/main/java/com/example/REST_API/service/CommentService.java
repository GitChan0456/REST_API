package com.example.REST_API.service;

import com.example.REST_API.Dto.CommentRequestDto;
import com.example.REST_API.Dto.CommentResponseDto;
import com.example.REST_API.Dto.PostResponseDto;
import com.example.REST_API.entity.Comment;
import com.example.REST_API.entity.Post;
import com.example.REST_API.entity.User;
import com.example.REST_API.exception.NotFoundException;
import com.example.REST_API.repository.CommentRepository;
import com.example.REST_API.repository.PostRepository;
import com.example.REST_API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor    // final과 NonNull이 붙은 변수를 인자들에 대한 생성자 자동 생성 (여기선 userRepository)
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 생성
    @Transactional
    public CommentResponseDto createComment(Long userId, Long postId, CommentRequestDto request) {
        User author = userRepository.findById(userId).orElseThrow(() ->
                        new NotFoundException("사용자를 찾을 수 없습니다."));

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new NotFoundException("게시글을 찾을 수 없습니다."));

        Comment comment = new Comment(
                post,
                request.getContent(),
                author
        );
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }


    // 조회
    @Transactional(readOnly=true)
    public CommentResponseDto getComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found"));

        return new CommentResponseDto(comment);
    }

    // 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto request){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        comment.changeContent(request.getContent());
        return new CommentResponseDto(comment);
    }

    // 삭제
    @Transactional
    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        commentRepository.deleteById(commentId);
    }


}
