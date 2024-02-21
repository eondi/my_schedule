package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleResponseDto2Test {

    @Test
    @DisplayName("ScheduleResponseDto2 test")
    public void ResponseDtoTest(){
        //given
        String username = "testUser";
        String password = "testPw";
        User testUser = new User(username, password);

        String title = "testTitle";
        String text = "testText";
        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto(title,text);


        Schedule testScedule = new Schedule(scheduleRequestDto,testUser);

        //when
        ScheduleResponseDto2 scheduleResponseDto = new ScheduleResponseDto2(testScedule, true);

        //then
        assertEquals("testTitle",scheduleResponseDto.getTitle());
        assertEquals("testUser",scheduleResponseDto.getUsername());
        assertTrue(scheduleResponseDto.getState());

    }

}