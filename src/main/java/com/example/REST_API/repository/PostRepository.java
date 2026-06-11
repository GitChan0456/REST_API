package com.example.REST_API.repository;

import com.example.REST_API.entity.Post;
import com.example.REST_API.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepository {
    // id 수동 증가
    private Long sequence_id = 1L;
    // <id, Post 객체> 형태로 저장하여 id와 매칭되는 Post 객체 다루기
    private final Map<Long, Post> store = new HashMap<>();

    public Post save(String title, String content, String image_url, User user) {
        Post post = new Post(sequence_id, title, content, image_url, user);
        store.put(sequence_id, post);
        sequence_id++;
        return post;
    }

    public Post findById(Long id) {
        return store.get(id);
    }

    public void deleteById(Long id){
        store.remove(id);
    }
}
