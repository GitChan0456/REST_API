package com.example.REST_API.controller;

import com.example.REST_API.Dto.LoginRequestDto;
import com.example.REST_API.Dto.UserRequestDto;
import com.example.REST_API.Dto.UserResponseDto;
import com.example.REST_API.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController                     // JSON으로 응답 반환
@RequestMapping("/users")        // /users 경로로 오는 요청들은 이 컨트롤러로 들어옴
@RequiredArgsConstructor           // NonNull 또는 final이 붙은 필드에 대한 생성자
public class UserController {
    // 서비스 객체를 하나 만들어서 서비스의 기능들을 자유자재로 사용할 것임
    private final UserService userService;

    /// 메서드로 들어오는 값 반환 되는 나가는 값 모두 Dto클래스를 거침 -> 요청과 응답의 역직렬화, 직렬화 과정을 나타냄

    // 유저 생성에는 {userId}가 필요하지 않음 나머지 Get, Put, Delete는 대상을 지정해야하므로 경로변수가 필요.
    @PostMapping("/signup")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    /*
    @PostMapping("/login")
    public UserResponseDto logineUser(@Valid @RequestBody LoginRequestDto request) {
        return userService.login(request);
    }
     */
    @PostMapping("/login")
    public UserResponseDto login(
            @Valid @RequestBody LoginRequestDto request,
            HttpSession session
    ) {
        UserResponseDto response = userService.login(request);

        session.setAttribute("LOGIN_USER_ID", response.getUser_id());

        return response;
    }

    @PatchMapping("/{userId}")
    public UserResponseDto patchUser(@PathVariable Long userId, @RequestBody UserRequestDto request ) {
        // 임시방편 Valid를 의도적으로 붙이지 않음
        return userService.patchUser(userId, request);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
