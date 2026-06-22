package com.example.REST_API.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "users")
@Getter
@RequiredArgsConstructor // final, @NonNull 붙은 변수들만 생성자로
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 값을 자동으로 증가시키도록(아이덴티티 방식으로)
    @Column(name = "user_id")
    private Long userId;
    private String email;
    private String password;
    private String nickname;
    private String profile_image;

    @OneToMany(mappedBy = "author")         // 한 명의 유저는 여러 게시글 작성 가능
    List<Post> posts = new ArrayList<>();   // Post 목록을 위한 Post 객체를 담는 List

    public User(String email, String password, String nickname, String profile_image) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profile_image = profile_image; // (선택) 이므로 없으면 null이 들어옴
    }

    // 서비스에 있지 않고 여기있는 이유는 서비스는 명령을 내리고 수행은 User 객체가 직접 수행
    public void changeNickname(String nickname){
        this.nickname = nickname;
    }
    public void changePassword(String password) {
        this.password = password;
    }
}
