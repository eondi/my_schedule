package com.sprata.my_schedule.entity;

import com.sprata.my_schedule.dto.CommentRequestDto;
import com.sprata.my_schedule.dto.CommentResponseDto;
import com.sprata.my_schedule.dto.ScheduleRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    @DisplayName("Comment 생성 test")
    public void createComment(){
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


        //when
        Comment TestComment = new Comment(commentRequestDto,testUser,testScedule);

        //then
        assertNull(TestComment.getNumber());
        assertEquals("testTitle",TestComment.getSchedule().getTitle());
        assertEquals("testText",TestComment.getSchedule().getText());
        assertFalse(TestComment.getSchedule().isState());
        assertEquals("testUser",TestComment.getUser().getUsername());
        assertEquals("testPw",TestComment.getUser().getPassword());
        assertEquals("testCommentText",TestComment.getText());


    }

}