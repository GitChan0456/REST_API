package com.example.REST_API.service;

import com.example.REST_API.Dto.LoginRequestDto;
import com.example.REST_API.Dto.UserRequestDto;
import com.example.REST_API.Dto.UserResponseDto;
import com.example.REST_API.entity.User;
import com.example.REST_API.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor    // final과 NonNull이 붙은 변수를 인자들에 대한 생성자 자동 생성 (여기선 userRepository)
public class UserService {
    private final UserRepository userRepository;

    // 생성
    public UserResponseDto createUser(UserRequestDto request){
        // 유저 레포지토리를 불러 요청값을 넘겨주면서 save() 메서드 호출하고 save가 응답용으로 반환하는 결과를 user변수에 저장
        User user = userRepository.save(request.getEmail(), request.getPassword(), request.getNickname(), request.getProfile_image() );
        return new UserResponseDto(user);
    }

    // 조회
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return new UserResponseDto(user);
    }

    // Patch
    public UserResponseDto patchUser(Long id, UserRequestDto request){
        // 어떤 동작인지 확인하고 분배 (컨트롤러 영역에 가깝지 않나)
        if (request.getNickname() != null){
            return updateNickname(id, request);
        }
        else if (request.getPassword() != null) {
            return updatePassword(id, request);
        }
        return null;
    }

    // 닉네임 업데이트인데 실제 변경은 User 클래스에서 일어남 왜?
    // 서비스는 이름 바꾸라는 명령만 전달할 뿐 바꾸는건 객체에서 일어남
    public UserResponseDto updateNickname(Long id, UserRequestDto request) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.changeNickname(request.getNickname());
        // (의문) request 바디 내용에 어차피 nickname요소만 들어왔는데 왜 getNickname()을 써야하는가?
        // (해결) Dto 객체형태로 통채로 넘어와서 다른 필드들은 null로 존재

        return new UserResponseDto(user);
    }
    public UserResponseDto updatePassword(Long id, UserRequestDto request) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.changePassword(request.getNickname());
        return new UserResponseDto(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }

    public UserResponseDto login(@Valid LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail());
        // 존재하지 않으면 예외던지고
        // 존재한다면 값 비교해 처리
        return new UserResponseDto(user);
    }
}
