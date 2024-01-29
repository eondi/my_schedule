package com.sprata.my_schedule.controller;

import com.sprata.my_schedule.Exception.PwNotFoundException;
import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto;
import com.sprata.my_schedule.entity.Schedule;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api")
public class ScheduleController  {

    private final Map<Integer, Schedule> scheduleList = new HashMap<>();


    // 일정 작성 기능
    @PostMapping("/write")
    public ScheduleResponseDto createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto){

        // RequestDto -> entity
        Schedule schedule  = new Schedule(requestDto);

        //id 부여
        int maxNumber = !scheduleList.isEmpty() ? scheduleList.size()+1 : 1;
        schedule.setNumber(maxNumber);

        // 비밀번호를 키값으로 둔 맵 생성 , DB저장
        scheduleList.put(schedule.getNumber(), schedule);

        // entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;

    }

    //일정 전체 조회 기능
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules(){

        List<ScheduleResponseDto> responseList = scheduleList.values().stream().sorted(Comparator.comparing(Schedule::getDate).reversed()).map(ScheduleResponseDto::new).toList();

        return  responseList;
    }

    //선택 일정 조회 기능
    @GetMapping("/schedules/{number}/{pw}")
    public ScheduleResponseDto getSchedule(@PathVariable Integer number, @PathVariable String pw) throws IllegalAccessException {
        // 비밀번호 확인
        List<ScheduleResponseDto> temp_list = checkPw(pw);

        List<ScheduleResponseDto> responseList = temp_list.stream().filter(schedule -> schedule.getNumber().equals(number)).toList();
        if (responseList.size() != 0){
            return  responseList.get(0);
        }
        return  null;
    }

    // 선택한 일정 수정 기능
    @PutMapping("/update/{number}/{pw}")
    public  ScheduleResponseDto updateSchedule(@PathVariable Integer number, @PathVariable String pw, @RequestBody ScheduleRequestDto requestDto) throws IllegalAccessException {
        // 비밀번호 확인
        List<ScheduleResponseDto> temp_list = checkPw(pw);

        // 해당 스케줄 존재확인
        ScheduleResponseDto ScheduleResponse = null;
        if (scheduleList.containsKey(number)) {
            // 해당 스케줄 가져오기
            Schedule schedule = scheduleList.get(number);
            schedule.update(requestDto);
            ScheduleResponse = new ScheduleResponseDto(schedule);
        }
        else {
            throw new IllegalAccessException(" 해당 스케줄이 존재하지않습니다");
        }
        return ScheduleResponse;

    }

    // 선택 스케줄 삭제
    @DeleteMapping("/delete/{number}/{pw}")
    public void deleteSchedule(@PathVariable Integer number, @PathVariable String pw) throws IllegalAccessException {
        // 비밀번호 확인
        List<ScheduleResponseDto> temp_list = checkPw(pw);

        if (scheduleList.containsKey(number)) {
            // 해당 스케줄 삭제
            scheduleList.remove(number);
        }
        else {
            throw new IllegalAccessException(" 해당 스케줄이 존재하지않습니다");
        }

    }

    // 비밀번호 확인
    public List<ScheduleResponseDto> checkPw(String pw) throws IllegalAccessException {
        List<ScheduleResponseDto> temp_list = scheduleList.values().stream().filter(schedule -> schedule.getPw().equals(pw)).map(ScheduleResponseDto::new).toList();
        if (!temp_list.isEmpty()) {
            return temp_list;
        }else {
            throw new IllegalAccessException("해당 비밀번호가 틀렸습니다");
        }
    }


}
