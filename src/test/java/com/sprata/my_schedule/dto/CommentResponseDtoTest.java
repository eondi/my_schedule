package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Comment;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentResponseDtoTest {
    @Test
    @DisplayName("CommentResponseDto test")
    public void ResponseDtoTest(){
        //given
        String username = "testUser";
        String password = "testPw";
        User testUser = new User(username, password);

        String title = "testTitle";
        String text = "testText";
        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto(title,text);


        Schedule testScedule = new Schedule(scheduleRequestDto,testUser);

        String comentText = "testCommentText";
        CommentRequestDto commentRequestDto = new CommentRequestDto(comentText);

        Comment TestComment = new Comment(commentRequestDto,testUser,testScedule);


        //when
        CommentResponseDto commentResponseDto = new CommentResponseDto(TestComment);

        //then
        assertEquals("testCommentText",commentResponseDto.getText());

    }

}