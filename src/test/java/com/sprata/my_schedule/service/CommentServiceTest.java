package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.CommentRequestDto;
import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.entity.Comment;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.repository.CommentRepository;
import com.sprata.my_schedule.repository.ScheduleRepository;
import com.sprata.my_schedule.responsentity.Message;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    CommentRepository commentRepository;

    @Mock
    ScheduleRepository scheduleRepository;

    CommentRequestDto requestDto;
    CommentRequestDto requestDto2;
    CommentRequestDto requestDto3;

    User user;


    Comment comment;
    Comment comment2;
    Comment comment3;

    @BeforeEach
    void setUp() {
        // given
        user = new User(1L,"TestUser", "testPw");

        ScheduleRequestDto ScRequestDto = new ScheduleRequestDto("testTitle", "testText");
        ScheduleRequestDto ScRequestDto2 = new ScheduleRequestDto("testTitle2", "testText2");
        user = new User(1L,"TestUser", "testPw");

        Schedule schedule = new Schedule(ScRequestDto, user,1);
        Schedule schedule2 = new Schedule(ScRequestDto2, user,2);

        requestDto = new CommentRequestDto("testText");
        requestDto2 = new CommentRequestDto("testText2");
        requestDto3 = new CommentRequestDto("testText3");

        comment = new Comment(requestDto, user, schedule,1L);
        comment2 = new Comment(requestDto2, user, schedule,1L);
        comment3 = new Comment(requestDto3, user, schedule2,1L);
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    public  void  updateCommentTest(){
        // given
        CommentService commentService = new CommentService(scheduleRepository,commentRepository);
        given(commentRepository.findById(1L)).willReturn(Optional.of(comment));

        // when
        ResponseEntity<Message> response =  commentService.updateComment(1L,requestDto3, user);

        // then
        assertEquals(200,response.getStatusCode().value());
        assertEquals("선택한 댓글의 수정 성공했습니다.",response.getBody().getMessage());

        JSONObject jObj = new JSONObject(response.getBody());
        JSONObject jObj2 = new JSONObject(jObj.get("data").toString());
        assertEquals("testText3",jObj2.get("text"));
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    public  void  updateCommentErrorTest(){
        // given
        CommentService commentService = new CommentService(scheduleRepository,commentRepository);

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            commentService.updateComment(4L,requestDto3, user);
        });
        // then
        assertEquals(
                "선택한 댓글이 존재하지 않습니다. ",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    public  void  deleteCommentTest(){
        // given
        CommentService commentService = new CommentService(scheduleRepository,commentRepository);
        given(commentRepository.findById(1L)).willReturn(Optional.of(comment));

        // when
        ResponseEntity<Message> response =  commentService.deleteComment(1L);

        // then
        assertEquals(200,response.getStatusCode().value());
        assertEquals("선택한 댓글의 삭제 성공했습니다.",response.getBody().getMessage());

    }

    @Test
    @DisplayName("댓글 삭제 에러 테스트")
    public  void  deleteCommentErrorTest(){
        // given
        CommentService commentService = new CommentService(scheduleRepository,commentRepository);

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            commentService.deleteComment(4L);
        });
        // then
        assertEquals(
                "선택한 댓글이 존재하지 않습니다. ",
                exception.getMessage()
        );
    }

}