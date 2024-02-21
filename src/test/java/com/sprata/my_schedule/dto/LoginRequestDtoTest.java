package com.sprata.my_schedule.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDtoTest {

    @Test
    @DisplayName("LoginRequestDto test")
    public void RequestDtoTest(){
        //given
        String username = "testUsername";
        String pw = "testPw";

        //when
        LoginRequestDto loginRequestDto = new LoginRequestDto(username,pw);

        //then
        assertEquals("testUsername",loginRequestDto.getUsername());
        assertEquals("testPw",loginRequestDto.getPassword());

    }

}