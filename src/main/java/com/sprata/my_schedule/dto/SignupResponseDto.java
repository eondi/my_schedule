package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignupResponseDto {
    private String username;
    private String msg;

    public SignupResponseDto(String username, String msg) {
        this.username = username;
        this.msg =msg;

    }
    }

    
