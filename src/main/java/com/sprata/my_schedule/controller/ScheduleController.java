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
    public ResponseEntity<Message> getSchedule(@PathVariable Integer number, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return  scheduleService.getSchedule(number, userDetails.getUser());
    }

    // 선택한 일정 수정 기능
    @PutMapping("/update/{number}")
    public ResponseEntity<Message>  updateSchedule(@PathVariable Integer number, @RequestBody ScheduleRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

        return scheduleService.updateSchedule(number, requestDto, userDetails.getUser());

    }

    //할일카드 완료 기능
    @PutMapping("/update-state/{number}")
    public ResponseEntity<Message>  updateScheduleState(@PathVariable Integer number,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

        return scheduleService.updateScheduleState(number, userDetails.getUser());

    }





}
