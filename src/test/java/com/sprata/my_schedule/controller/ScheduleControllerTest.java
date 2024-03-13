package com.sprata.my_schedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sprata.my_schedule.config.WebSecurityConfig;
import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.SignupRequestDto;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.repository.ScheduleRepository;
import com.sprata.my_schedule.repository.UserRepository;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import com.sprata.my_schedule.security.UserDetailsImpl;
import com.sprata.my_schedule.service.ScheduleService;
import com.sprata.my_schedule.service.UserService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
class ScheduleControllerTest {
    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ScheduleController scheduleController;

    @MockBean
    ScheduleService scheduleService;
    @MockBean
    ScheduleRepository scheduleRepository;

    List<Schedule> scheduleList = new ArrayList<>();
    Schedule schedule;
    Schedule schedule2;

    User user;

    ScheduleRequestDto requestDto;
    ScheduleRequestDto requestDto2;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
        requestDto = new ScheduleRequestDto("testTitle", "testText");
        requestDto2 = new ScheduleRequestDto("testTitle2", "testText2");
        user = new User(1L,"TestUser", "testPw");

        schedule = new Schedule(requestDto, user,1);
        schedule2 = new Schedule(requestDto2, user,2);

//        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);
        scheduleList.add(schedule2);
    }

    private void mockUserSetup() {
        // Mock 테스트 유져 생성
        String username = "TestUser";
        String password = "TestPw";
        User testUser = new User(username, password);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("일정 작성 테스트")
    void writeTest() throws Exception {

        // given
        this.mockUserSetup();
        ScheduleRequestDto requestDto = new ScheduleRequestDto("TestTitle","TestText");
        String postInfo = objectMapper.writeValueAsString(requestDto);

        // when - then
        ResultActions resultActions  = mvc.perform(post("/api/schedules/write")
                .content(postInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
        );


        resultActions.andExpect(status().isOk());
    }

//    @Test
//    @DisplayName("일정 전체 조회 테스트")
//    void selectALlTest() throws Exception {
//
//        // given
//        this.mockUserSetup();
//        ScheduleRequestDto requestDto = new ScheduleRequestDto("TestTitle","TestText");
//        String postInfo = objectMapper.writeValueAsString(requestDto);
//        doReturn(scheduleList).when(scheduleRepository).findAll();
//        Message message = new Message();
//        message.setStatus(StatusEnum.OK);
//        message.setMessage("일정 전체 조회를 성공했습니다.");
//        message.setData(new HashMap<>());
//
//        given(scheduleService.getAllSchedules()).willReturn(message);
//
//        // when - then
//        ResultActions resultActions  = mvc.perform(get("/api/schedules/all")
//                .contentType(MediaType.APPLICATION_JSON)
//                .principal(mockPrincipal)
//        );
//
//        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
//
//        resultActions.andExpect(status().isOk());
//        resultActions.andExpect(content().json("{'status':'OK','message': '일정 전체 조회를 성공했습니다.', 'data':{}}"));
//    }

    @Test
    @DisplayName("일정 일부 조회 테스트")
    void selectOneTest() throws Exception {

        // given
        this.mockUserSetup();
        ScheduleRequestDto requestDto = new ScheduleRequestDto("TestTitle","TestText");
        given(scheduleRepository.findById(1)).willReturn(Optional.of(schedule));
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("일정 일부 조회를 성공했습니다.");
        message.setData(new HashMap<>());

        given(scheduleService.getSchedule(1,user)).willReturn(message);

        // when - then
        ResultActions resultActions  = mvc.perform(get("/api/schedules/{number}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
        );

        resultActions.andExpect(content().json("{'status':'OK','message': '일정 일부 조회를 성공했습니다.', 'data':{}}"));

    }

    @Test
    @DisplayName("일정 수정 테스트")
    void updateTest() throws Exception {

        // given
        this.mockUserSetup();
        ScheduleRequestDto requestDto = new ScheduleRequestDto("TestTitle","TestText");
        String postInfo = objectMapper.writeValueAsString(requestDto);

        given(scheduleRepository.findById(1)).willReturn(Optional.of(schedule));
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("일정 수정을 성공했습니다.");
        message.setData(new HashMap<>());

        given(scheduleService.updateSchedule(1, requestDto, user)).willReturn(message);

        // when - then
        ResultActions resultActions  = mvc.perform(put("/api/schedules/update/{number}",1)
                .content(postInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
        );

        resultActions.andExpect(status().isOk());

    }

    @Test
    @DisplayName("일정 상태 변경 테스트")
    void ScheduleState() throws Exception {

        // given
        this.mockUserSetup();
        ScheduleRequestDto requestDto = new ScheduleRequestDto("TestTitle","TestText");
        given(scheduleRepository.findById(1)).willReturn(Optional.of(schedule));
        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("일정 상태 변경을 성공했습니다.");
        message.setData(new HashMap<>());

        given(scheduleService.updateScheduleState(1,user)).willReturn(message);

        // when - then
        ResultActions resultActions  = mvc.perform(get("/api/update-state/{number}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
        );

        resultActions.andExpect(status().isOk());

    }

}