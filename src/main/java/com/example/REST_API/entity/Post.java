package com.example.REST_API.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Post {
    private final Long post_id;            // 포스트 식별 id
    private String title;
    private String content;
    private User author;        // 작성자 정보로 User 객체 사용

    private String image_url;   // 첨부 이미지 경로
    private Long like_count;    // 좋아요 수
    private Long visited_count; // 방문자 수
    private Long comment_count; // 댓글 수
    private String write_time;  // 작성시간

    List<Comment> comments = new ArrayList<>();   // Post 목록을 위한 Post 객체를 담는 List

    public Post(Long id, String title, String content, String image_url, User user){
        this.post_id = id;
        this.title = title;
        this.content = content;
        this.image_url = image_url;
        this.like_count = 0L;
        this.visited_count = 0L;
        this.comment_count = 0L;
        this.author = user;
        setWrite_time();
    }

    public void visited_count_up() {
        this.visited_count++;
    }
    public void setWrite_time(){
        this.write_time = "2026-06-01 01:00:00"; // 작성 날짜(임시) 실제로는 현재 시간을 가져와서 넣어야함
    }

    public void changeTitle(String title) {
        this.title = title;
        setWrite_time();
    }
    public void changeContent(String content) {
        this.content = content;
        setWrite_time();
    }
    public void changeImageFile(String image_url) {
        this.image_url = image_url;
        setWrite_time();
    }

}
