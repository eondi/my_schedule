package com.sprata.my_schedule.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    @Test
    @DisplayName("User생성 test")
    public void create(){

        //given
        String name = "Testname";
        String pw = "TestPw";
        //when
        User userTest =new User (name, pw);

        //then
        assertNull(userTest.getId());
        assertEquals("Testname", userTest.getUsername());
        assertEquals("TestPw", userTest.getPassword());

    }

}