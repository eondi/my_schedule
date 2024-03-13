package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.SignupRequestDto;
import com.sprata.my_schedule.entity.User;
import  com.sprata.my_schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    /**
     * 유저 생성
     * @param requestDto 유저 생성 요청정보
     * @return 유저 생성 결과
     */
    public void signup(SignupRequestDto requestDto);
}