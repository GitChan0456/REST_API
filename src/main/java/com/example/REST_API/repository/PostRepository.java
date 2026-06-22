package com.example.REST_API.repository;

import com.example.REST_API.entity.Post;
import com.example.REST_API.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}