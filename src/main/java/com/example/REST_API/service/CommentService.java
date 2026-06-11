package com.example.REST_API.service;

import com.example.REST_API.Dto.CommentRequestDto;
import com.example.REST_API.Dto.CommentResponseDto;
import com.example.REST_API.Dto.PostResponseDto;
import com.example.REST_API.entity.Comment;
import com.example.REST_API.entity.Post;
import com.example.REST_API.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor    // final과 NonNull이 붙은 변수를 인자들에 대한 생성자 자동 생성 (여기선 userRepository)
public class CommentService {
    private final CommentRepository commentRepository;

    // 생성
    public CommentResponseDto createComment(CommentRequestDto request){
        Comment com = commentRepository.save(request.getContent(), request.getUser());
        return new CommentResponseDto(com);
    }

    // 조회
    public CommentResponseDto getComment(Long id){
        Comment com = commentRepository.findById(id);
        if (com == null) {
            throw new IllegalArgumentException("Comment not found");
        }

        return new CommentResponseDto(com);
    }

    // 수정
    public CommentResponseDto updateComment(Long id, CommentRequestDto request){
        Comment com = commentRepository.findById(id);
        if (com == null) {
            throw new IllegalArgumentException("Comment not found");
        }
        com.changeContent(request.getContent());
        return new CommentResponseDto(com);
    }

    // 삭제
    public void deleteComment(Long id){
        Comment com = commentRepository.findById(id);
        if (com == null) {
            throw new IllegalArgumentException("Comment not found");
        }
        commentRepository.deleteById(id);
    }

}
