package com.sprata.my_schedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprata.my_schedule.config.WebSecurityConfig;
import com.sprata.my_schedule.dto.CommentRequestDto;
import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.entity.Comment;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.repository.ScheduleRepository;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import com.sprata.my_schedule.security.UserDetailsImpl;
import com.sprata.my_schedule.service.CommentService;
import com.sprata.my_schedule.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {ScheduleController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
class CommentControllerTest {
    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ScheduleController scheduleController;

    @MockBean
    CommentService commentService;

    CommentRequestDto requestDto;
    CommentRequestDto requestDto2;
    CommentRequestDto requestDto3;

    User user;


    Comment comment;
    Comment comment2;
    Comment comment3;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
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

    private void mockUserSetup() {
        // Mock 테스트 유저 생성
        String username = "TestUser";
        String password = "TestPw";
        User testUser = new User(username, password);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("댓글 작성 테스트")
    void writeTest() throws Exception {

        // given
        this.mockUserSetup();
        CommentRequestDto requestDto = new CommentRequestDto("TestText");
        String postInfo = objectMapper.writeValueAsString(requestDto);
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("댓글 작성을  성공했습니다.");
        message.setData(new HashMap<>());

        given(commentService.createComment(1,requestDto,user)).willReturn(message);

        // when - then
        ResultActions resultActions  = mvc.perform(post("/api/comments/create/{number}",1)
                .content(postInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
        );

        resultActions.andExpect(status().isOk());
    }

}