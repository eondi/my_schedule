package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Comment;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleResponseDtoTest {

    @Test
    @DisplayName("ScheduleResponseDto test")
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
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(testScedule);

        //then
        assertEquals("testText",scheduleResponseDto.getText());
        assertEquals("testTitle",scheduleResponseDto.getTitle());
        assertEquals("testUser",scheduleResponseDto.getUsername());
        assertNull(scheduleResponseDto.getNumber());
        assertNull(scheduleResponseDto.getCreatedAt());

    }

}