package com.example.REST_API.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor    // 필수 인자 생성자 생성(본인초기화) NonNull, final
public class Comment {
    private final Long comment_id;
    private String content;
    private User author;        // 작성자 정보로 User 객체 사용
    private String write_time;  // 작성시간

    public Comment(Long id, String content, User user){
        this.comment_id = id;
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