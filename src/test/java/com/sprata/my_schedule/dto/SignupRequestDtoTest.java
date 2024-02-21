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
        String title = "testTitle";
        String text = "testText";

        //when
        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto(title,text);

        //then
        assertEquals("testTitle",scheduleRequestDto.getTitle());
        assertEquals("testText",scheduleRequestDto.getText());

    }


}