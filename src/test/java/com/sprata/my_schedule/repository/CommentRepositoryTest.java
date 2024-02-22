package com.sprata.my_schedule.repository;

import com.sprata.my_schedule.dto.CommentRequestDto;
import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.entity.Comment;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Test
    @DisplayName("댓글 추가")
    void addCommentTest() {
        // given
        User user = new User("name", "pw");
        ScheduleRequestDto scheduleRequestDto = new ScheduleRequestDto("testTitle","testText");
        Schedule schedule = new Schedule(scheduleRequestDto, user);
        CommentRequestDto  commentRequestDto = new CommentRequestDto("commtnetTest");
        Comment comment = new Comment(commentRequestDto, user, schedule);

        // when
        Comment savedComment= commentRepository.save(comment);

        // then
        assertEquals(savedComment.getUser().getUsername(),user.getUsername());
        assertEquals(savedComment.getUser().getPassword(),user.getPassword());
        assertEquals(savedComment.getText(),comment.getText());
        assertEquals(savedComment.getSchedule().getTitle(),schedule.getTitle());
    }

}