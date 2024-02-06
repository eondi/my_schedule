package com.sprata.my_schedule.controller;

import com.sprata.my_schedule.Exception.PwNotFoundException;
import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.security.UserDetailsImpl;
import com.sprata.my_schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController  {

    private final ScheduleService scheduleService;


    // 일정 작성 기능
    @PostMapping("/write")
    public ResponseEntity<Message> createProduct(@RequestBody ScheduleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return scheduleService.createSchedule(requestDto, userDetails.getUser());
    }

    //회원별로 각각 나누어서 일정 전체 조회 기능
    @GetMapping("/all")
    public ResponseEntity<Message> getAllSchedules(){
        System.out.println("here");
        return  scheduleService.getAllSchedules();
    }

    //선택 일정 조회 기능
    @GetMapping("/{number}")
    public ResponseEntity<Message> getSchedule(@PathVariable Integer number, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

        return  scheduleService.getSchedule(number, userDetails.getUser());
    }
//
//    // 선택한 일정 수정 기능
//    @PutMapping("/update/{number}/{pw}")
//    public  ScheduleResponseDto updateSchedule(@PathVariable Integer number, @PathVariable String pw, @RequestBody ScheduleRequestDto requestDto) throws IllegalAccessException {
//        // 비밀번호 확인
//        List<ScheduleResponseDto> temp_list = checkPw(pw);
//
//        // 해당 스케줄 존재확인
//        ScheduleResponseDto ScheduleResponse = null;
//        if (scheduleList.containsKey(number)) {
//            // 해당 스케줄 가져오기
//            Schedule schedule = scheduleList.get(number);
//            schedule.update(requestDto);
//            ScheduleResponse = new ScheduleResponseDto(schedule);
//        }
//        else {
//            throw new IllegalAccessException(" 해당 스케줄이 존재하지않습니다");
//        }
//        return ScheduleResponse;
//
//    }
//
//    // 선택 스케줄 삭제
//    @DeleteMapping("/delete/{number}/{pw}")
//    public void deleteSchedule(@PathVariable Integer number, @PathVariable String pw) throws IllegalAccessException {
//        // 비밀번호 확인
//        List<ScheduleResponseDto> temp_list = checkPw(pw);
//
//        if (scheduleList.containsKey(number)) {
//            // 해당 스케줄 삭제
//            scheduleList.remove(number);
//        }
//        else {
//            throw new IllegalAccessException(" 해당 스케줄이 존재하지않습니다");
//        }
//
//    }
//
//    // 비밀번호 확인
//    public List<ScheduleResponseDto> checkPw(String pw) throws IllegalAccessException {
//        List<ScheduleResponseDto> temp_list = scheduleList.values().stream().filter(schedule -> schedule.getPw().equals(pw)).map(ScheduleResponseDto::new).toList();
//        if (!temp_list.isEmpty()) {
//            return temp_list;
//        }else {
//            throw new IllegalAccessException("해당 비밀번호가 틀렸습니다");
//        }
//    }


}
