package com.example.REST_API.service;

import com.example.REST_API.Dto.LoginRequestDto;
import com.example.REST_API.Dto.UserRequestDto;
import com.example.REST_API.Dto.UserResponseDto;
import com.example.REST_API.entity.User;
import com.example.REST_API.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor    // final과 NonNull이 붙은 변수를 인자들에 대한 생성자 자동 생성 (여기선 userRepository)
public class UserService {
    private final UserRepository userRepository;

    // 생성
    @Transactional
    public UserResponseDto createUser(UserRequestDto request) {
        User user = new User(
                request.getEmail(),
                request.getPassword(),
                request.getNickname(),
                request.getProfile_image()
        );
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser);
    }

    // 조회
    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new UserResponseDto(user);
    }

    // Patch
    public UserResponseDto patchUser(Long userId, UserRequestDto request){
        // 어떤 동작인지 확인하고 분배 (컨트롤러 영역에 가깝지 않나)
        if (request.getNickname() != null){
            return updateNickname(userId, request);
        }
        else if (request.getPassword() != null) {
            return updatePassword(userId, request);
        }
        return null;
    }

    // 닉네임 업데이트인데 실제 변경은 User 클래스에서 일어남 왜?
    // 서비스는 이름 바꾸라는 명령만 전달할 뿐 바꾸는건 객체에서 일어남
    @Transactional
    public UserResponseDto updateNickname(Long userId, UserRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.changeNickname(request.getNickname());

        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updatePassword(Long userId, UserRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.changePassword(request.getPassword());
        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.deleteById(userId);
    }


    public UserResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid email or password"
                        )
                );

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException(
                    "Invalid email or password"
            );
        }

        return new UserResponseDto(user);
    }
}
