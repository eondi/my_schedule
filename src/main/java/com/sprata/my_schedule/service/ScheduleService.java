package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto2;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.repository.ScheduleRepository;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    Message message = new Message();
    HttpHeaders headers= new HttpHeaders();
//  headers.setContentType(new MediaType("application", "json",Charset.forName("UTF-8")));


    public ResponseEntity<Message>  createSchedule(ScheduleRequestDto requestDto, User user) {
        Schedule schedule = scheduleRepository.save(new Schedule(requestDto, user));
        message.setStatus(StatusEnum.OK);
        message.setMessage("일정이 성공적으로 작성되었습니다.");
        message.setData(new ScheduleResponseDto(schedule));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    public ResponseEntity<Message> getAllSchedules() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        HashMap<String, List<ScheduleResponseDto2>> scheduleHashMap = new HashMap<>();

        for (Schedule schedule: scheduleList){
            if(!scheduleHashMap.containsKey(schedule.getUser().getUsername())){
                List<ScheduleResponseDto2> ScheduleList = new ArrayList<>();
                ScheduleList.add(new ScheduleResponseDto2(schedule, schedule.isState()));
                scheduleHashMap.put(schedule.getUser().getUsername(), ScheduleList);
            }else{
                scheduleHashMap.get(schedule.getUser().getUsername()).add(new ScheduleResponseDto2(schedule, schedule.isState()));
            }
        }

        // 정렬
        for (String name : scheduleHashMap.keySet()){
            Stream<ScheduleResponseDto2> temp = scheduleHashMap.get(name).stream().sorted(Comparator.comparing(ScheduleResponseDto2::getCreatedAt).reversed());
            scheduleHashMap.put(name, temp.toList());
        }


        message.setStatus(StatusEnum.OK);
        message.setMessage("일정 전체 조회를 성공했습니다.");
        message.setData(scheduleHashMap);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);


    }
}
