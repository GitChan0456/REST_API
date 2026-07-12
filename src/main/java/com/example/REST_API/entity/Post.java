package com.example.REST_API.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;        // 작성자 정보로 User 객체 사용
    private String title;
    private String content;

    @Column(name = "image_url")
    private String imageUrl;   // 첨부 이미지 경로
    private Long likeCount;    // 좋아요 수
    private Long visitedCount; // 방문자 수
    private Long commentCount; // 댓글 수
    private String write_time;  // 작성시간

    @OneToMany(mappedBy = "post")
    List<Comment> comments = new ArrayList<>();   // Post 목록을 위한 Post 객체를 담는 List

    public Post(String title, String content, String imageUrl, User user){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.author = user;

        this.likeCount = 0L;
        this.visitedCount = 0L;
        this.commentCount = 0L;

        setWrite_time();
    }

    public void visitedCountUp() {
        this.visitedCount++;
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
    public void changeImageFile(String imageUrl) {
        this.imageUrl = imageUrl;
        setWrite_time();
    }

}
