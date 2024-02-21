package com.sprata.my_schedule.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoDtoTest {
    @Test
    @DisplayName("UserInfoDto test")
    public void UserInfoTest(){
        //given
        String userName = "test_username";

        //when
        UserInfoDto userInfoDto = new UserInfoDto(userName);

        //then
        assertEquals("test_username",userInfoDto.getUsername());

    }

}