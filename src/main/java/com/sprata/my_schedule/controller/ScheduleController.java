package com.sprata.my_schedule.controller;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import com.sprata.my_schedule.security.UserDetailsImpl;
import com.sprata.my_schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController  {

    private final ScheduleService scheduleService;
    Message message = new Message();
    HttpHeaders headers= new HttpHeaders();


    // 일정 작성 기능
    @PostMapping("/write")
    public ResponseEntity<Message> createProduct(@RequestBody ScheduleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return scheduleService.createSchedule(requestDto, userDetails.getUser());
    }

    //회원별로 각각 나누어서 일정 전체 조회 기능
    @GetMapping("/all")
    public ResponseEntity<Message> getAllSchedules(@RequestParam("page") int page,
                                                   @RequestParam("size") int size,
                                                   @RequestParam("sortBy") String sortBy,
                                                   @RequestParam("isAsc") boolean isAsc){
        Page<ScheduleResponseDto> page_result = scheduleService.getAllSchedules(page -1, size, sortBy, isAsc);
        message.setStatus(StatusEnum.OK);
        message.setMessage("일정 전체 조회를 성공했습니다.");
        message.setData(page_result);

        return  new ResponseEntity<>(message, HttpStatus.OK );
    }

    //선택 일정 조회 기능
    @GetMapping("/{number}")
    public ResponseEntity<Message> getSchedule(@PathVariable Integer number, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return new ResponseEntity<>(scheduleService.getSchedule(number, userDetails.getUser()), HttpStatus.OK);
    }

    // 선택한 일정 수정 기능
    @PutMapping("/update/{number}")
    public ResponseEntity<Message>  updateSchedule(@PathVariable Integer number, @RequestBody ScheduleRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

        return new ResponseEntity<>(scheduleService.updateSchedule(number, requestDto, userDetails.getUser()), HttpStatus.OK);

    }

    //할일카드 완료 기능
    @PutMapping("/update-state/{number}")
    public ResponseEntity<Message>  updateScheduleState(@PathVariable Integer number,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {

        return  new ResponseEntity<>(scheduleService.updateScheduleState(number, userDetails.getUser()), HttpStatus.OK);

    }





}
