package com.example.REST_API.repository;

import com.example.REST_API.Dto.UserResponseDto;
import com.example.REST_API.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    // id 수동 증가
    private Long sequence_id = 1L;

    // <id, User 객체> 형태로 저장하여 id와 매칭되는 User 객체 다루기
    private final Map<Long, User> store = new HashMap<>();

    /// 원래 JPA 사용시 자동으로 JpaRepository라는 인터페이스가 아래 save, findById, deleteById 등을 기본 제공해줘서 따로 정의하지 않아도 됨
    public User save(String email, String password, String nickname, String profile_image) {
        User user = new User(sequence_id, email, password, nickname, profile_image);
        store.put(sequence_id, user);
        sequence_id++;
        return user;
    }
    // 1. user 객체 생성하여 요청으로 들어온 값으로 초기값 넣고
    // 2. 해시 맵에 <id, user> 형태로 저장
    // 3. id가 생성되고 나면 자동증가
    // 4. 응답 결과로 사용할 user 객체 return

    public User findById(Long id) {
        return store.get(id);
    }
    public void deleteById(Long id){
        store.remove(id);
    }
}