package com.example.REST_API.repository;

import com.example.REST_API.entity.Comment;
import com.example.REST_API.entity.Post;
import com.example.REST_API.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CommentRepository {
    // id 수동 증가
    private Long sequence_id = 1L;
    // <id, Comment 객체> 형태로 저장하여 id와 매칭되는 Comment 객체 다루기
    private final Map<Long, Comment> store = new HashMap<>();

    public Comment save(String content, User user) {
        Comment com = new Comment(sequence_id, content, user);
        store.put(sequence_id, com);
        sequence_id++;
        return com;
    }

    public Comment findById(Long id) {
        return store.get(id);
    }
    public void deleteById(Long id){
        store.remove(id);
    }
}
