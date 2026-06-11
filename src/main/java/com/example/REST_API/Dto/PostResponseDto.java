package com.example.REST_API.Dto;

import com.example.REST_API.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long post_id;
    private String title;
    private String content;
    private String author_nickname;

    private String image_url;   // 첨부 이미지 경로
    private Long like_count;    // 좋아요 수
    private Long visited_count; // 방문자 수
    private Long comment_count; // 댓글 수
    private String write_time;  // 작성시간

    public PostResponseDto(Post post){
        this.post_id = post.getPost_id();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.image_url = post.getImage_url();

        this.like_count = post.getLike_count();
        this.comment_count = post.getComment_count();
        this.visited_count = post.getVisited_count();
        this.write_time = post.getWrite_time();

        this.author_nickname = post.getAuthor().getNickname();   // User 객체를 가져오고 그 다음 거기서 또 id를 가져옴
    }
}
