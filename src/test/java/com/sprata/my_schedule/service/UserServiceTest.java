package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.SignupRequestDto;

import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 중복 확인 테스트")
    public void signupDupTest(){
        // given
        SignupRequestDto requestDto = new SignupRequestDto("TestUser","TestPw");
        UserService userService = new UserServiceImpl(userRepository,passwordEncoder);
        User user = new User("TestUser","TestPw");
        given(userRepository.findByUsername("TestUser")).willReturn(Optional.of(user));

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.signup(requestDto);
        });
        // then
        assertEquals(
                "중복된 사용자가 존재합니다.",
                exception.getMessage()
        );



    }

}