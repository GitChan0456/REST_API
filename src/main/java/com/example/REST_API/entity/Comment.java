package com.example.REST_API.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor    // 필수 인자 생성자 생성(본인초기화) NonNull, final
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long comment_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private String content;
    private String write_time;  // 작성시간

    public Comment( Post post, String content, User user){
        this.post = post;
        this.content = content;
        this.author = user;
        setWrite_time();
    }
    public void setWrite_time(){
        this.write_time = "2026-06-01 01:00:00"; // 작성 날짜(임시) 실제로는 현재 시간을 가져와서 넣어야함
    }

    public void changeContent(String content) {
        this.content = content;
        setWrite_time();
    }


}