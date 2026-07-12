package com.example.REST_API.Dto;

import com.example.REST_API.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private Long authorId;
    private String author_nickname;

    private String imageUrl;   // 첨부 이미지 경로
    private Long likeCount;    // 좋아요 수
    private Long visitedCount; // 방문자 수
    private Long commentCount; // 댓글 수
    private String write_time;  // 작성시간

    public PostResponseDto(Post post){
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();

        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.visitedCount = post.getVisitedCount();
        this.write_time = post.getWrite_time();
        this.authorId = post.getAuthor().getUserId();
        this.author_nickname = post.getAuthor().getNickname();   // User 객체를 가져오고 그 다음 거기서 또 id를 가져옴
    }
}
