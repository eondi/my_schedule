package com.sprata.my_schedule.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentRequestDtoTest {

    @Test
    @DisplayName("CommentRequestDto test")
    public void RequestDtoTest(){
        //given;
        String text = "testText";

        //when
        CommentRequestDto commentRequestDto = new CommentRequestDto(text);

        //then
        assertEquals("testText",commentRequestDto.getText());

    }

}