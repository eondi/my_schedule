package com.sprata.my_schedule.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleRequestDtoTest {
    @Test
    @DisplayName("ScheduleRequestDto test")
    public void RequestDtoTest(){
        //given
        String title = "test_title";
        String text = "test_text";

        //when
        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto(title,text);

        //then
        assertEquals("test_title",scheduleRequestDto.getTitle());
        assertEquals("test_text",scheduleRequestDto.getText());

    }

}