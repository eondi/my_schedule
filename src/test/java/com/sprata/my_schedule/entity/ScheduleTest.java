package com.sprata.my_schedule.entity;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {



    Schedule schedule;

    @Test
    @DisplayName("Schedule 생성 test")
    public  void createSchedule(){

        //given
        String username = "testUser";
        String password = "testPw";
        User testUser = new User(username, password);

        String title = "testTitle";
        String text = "testText";
        ScheduleRequestDto requestDto = new ScheduleRequestDto(title,text);


        //when
        Schedule TestSchedule = new Schedule(requestDto,testUser);

        //then
        assertNull(TestSchedule.getNumber());
        assertEquals("testTitle",TestSchedule.getTitle());
        assertEquals("testText",TestSchedule.getText());
        assertFalse(TestSchedule.isState());
        assertEquals("testUser",TestSchedule.getUser().getUsername());
        assertEquals("testPw",TestSchedule.getUser().getPassword());

    }

}