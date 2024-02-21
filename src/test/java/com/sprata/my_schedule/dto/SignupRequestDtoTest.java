package com.sprata.my_schedule.dto;

import jakarta.validation.Valid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupRequestDtoTest {

    @Test
    @DisplayName("SignupRequestDto test")
    public void RequestDtoTest(){
        //given
        String username = "test_username";
        String pw = "test_pw";

        //when
        SignupRequestDto signupRequestDto = new SignupRequestDto(username,pw);

        //then
        assertEquals("test_username",signupRequestDto.getUsername());
        assertEquals("test_pw",signupRequestDto.getPassword());

    }



}