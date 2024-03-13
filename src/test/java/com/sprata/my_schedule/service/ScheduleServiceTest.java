package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto2;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
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

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {
    @Mock
    ScheduleRepository scheduleRepository;
    List<Schedule> scheduleList = new ArrayList<>();
    Schedule schedule;
    Schedule schedule2;

    User user;

    ScheduleRequestDto requestDto;
    ScheduleRequestDto requestDto2;



    @BeforeEach
    void setUp() {
        // given
        requestDto = new ScheduleRequestDto("testTitle", "testText");
        requestDto2 = new ScheduleRequestDto("testTitle2", "testText2");
        user = new User(1L,"TestUser", "testPw");

        schedule = new Schedule(requestDto, user,1);
        schedule2 = new Schedule(requestDto2, user,2);

//        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);
        scheduleList.add(schedule2);
    }



//    @Test
//    @DisplayName("게시글 전체 조회 테스트")
//    public  void  getAllSchedulesTest(){
//        // given
//        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleRepository);
//        doReturn(scheduleList).when(scheduleRepository).findAll();
//
//        // when
//        Message message = scheduleService.getAllSchedules();
//
//        // then
//        assertEquals("일정 전체 조회를 성공했습니다.",message.getMessage());
//
//    }

    @Test
    @DisplayName("게시글 선택 조회 테스트")
    public  void getScheduleTest(){
        // given
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleRepository);
        given(scheduleRepository.findById(1)).willReturn(Optional.of(schedule));

        // when
        Message message =  scheduleService.getSchedule(1,user);

        // then
        assertEquals("선택한 일정의 정보 조회를 성공했습니다.",message.getMessage());

    }

    @Test
    @DisplayName("게시글 선택 조회 에러 테스트")
    public  void getScheduleErrorTest(){
        // given
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleRepository);

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scheduleService.getSchedule(3,user);
        });
        // then
        assertEquals(
                "선택한 할일이 존재하지 않습니다. ",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    public  void updateScheduleTest(){
        // given
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleRepository) {
        };
        given(scheduleRepository.findById(1)).willReturn(Optional.of(schedule));

        // when
        Message message =  scheduleService.updateSchedule(1, requestDto2,user);

        // then
        assertEquals("선택한 일정의 수정 성공했습니다.",message.getMessage());
    }

    @Test
    @DisplayName("게시글 수정 에러 테스트 : 사용자가 다른 경우")
    public  void updateScheduleErrorTest(){
        // given
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleRepository);
        given(scheduleRepository.findById(1)).willReturn(Optional.of(schedule));
        User user = new User(2L,"testUser2", "testUser2");

        // when
        Message message =  scheduleService.updateSchedule(1, requestDto2,user);

        // then
        assertEquals("작성자의 할일이 존재하지 않습니다 ",message.getMessage());
    }

    @Test
    @DisplayName("게시글 상태 완료 변경 테스트")
    public void updateScheduleStateTest(){
        // given
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleRepository);
        given(scheduleRepository.findById(1)).willReturn(Optional.of(schedule));

        // when
        Message message =  scheduleService.updateScheduleState(1, user);

        // then
        assertEquals("1 가 완료 상태로 변경되었습니다.",message.getMessage());
    }


}