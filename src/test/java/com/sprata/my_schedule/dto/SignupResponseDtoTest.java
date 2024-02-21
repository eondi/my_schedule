package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupResponseDtoTest {
    @Test
    @DisplayName("SignupResponseDto test")
    public void ResponseDtoTest(){
        //given
        String username = "testUser";
        String msg = "testMSG";

        //when
        SignupResponseDto signupResponseDto = new SignupResponseDto(username, msg);

        //then
        assertEquals("testUser",signupResponseDto.getUsername());
        assertEquals("testMSG",signupResponseDto.getMsg());

    }
}