package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto2;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.entity.User;
import com.sprata.my_schedule.repository.ScheduleRepository;
import com.sprata.my_schedule.responsentity.Message;
import com.sprata.my_schedule.responsentity.StatusEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class ScheduleServiceImpl implements  ScheduleService{
    private final ScheduleRepository scheduleRepository;
    Message message = new Message();
    HttpHeaders headers= new HttpHeaders();


    @Override
    public ResponseEntity<Message>  createSchedule(ScheduleRequestDto requestDto, User user) {
        Schedule schedule = scheduleRepository.save(new Schedule(requestDto, user));

        message.setStatus(StatusEnum.OK);
        message.setMessage("일정이 성공적으로 작성되었습니다.");
        message.setData(new ScheduleResponseDto(schedule));

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @Override
    public Page<ScheduleResponseDto> getAllSchedules(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Schedule> schedulePageList = scheduleRepository.findAll(pageable);


        Page<ScheduleResponseDto> scheduleList = schedulePageList.map(ScheduleResponseDto::new);

        return scheduleList;


    }

    @Override
    public Message getSchedule(Integer number, User user) {

        message.setStatus(StatusEnum.OK);

        Schedule schedule = findScedule(number,user);

        if (message.getStatus().equals(StatusEnum.NOT_FOUND)){
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("선택한 할일이 존재하지 않습니다. ");

            return message;
        }

        message.setStatus(StatusEnum.OK);
        message.setMessage("선택한 일정의 정보 조회를 성공했습니다.");
        message.setData(new ScheduleResponseDto(schedule));

        return message;
    }


    private Schedule findScedule(Integer number, User user) {
        Schedule schedule = scheduleRepository.findById(number).orElseThrow(() -> new IllegalArgumentException("선택한 할일이 존재하지 않습니다. "));
        if(!schedule.getUser().getUsername().equals(user.getUsername())){
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("작성자의 할일이 존재하지 않습니다 ");
            message.setData(null);
        }
        return schedule;
    }

    @Override
    @Transactional
    public Message updateSchedule(Integer number, ScheduleRequestDto requestDto, User user) {
        message.setStatus(StatusEnum.OK);
        Schedule schedule = findScedule(number,user);
        if (message.getStatus().equals(StatusEnum.NOT_FOUND)){
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("작성자의 할일이 존재하지 않습니다 ");
            return message;
        }

        schedule.update(requestDto);

        message.setStatus(StatusEnum.OK);
        message.setMessage("선택한 일정의 수정 성공했습니다.");
        message.setData(new ScheduleResponseDto(schedule));

        return message;

    }
    @Override
    @Transactional
    public Message updateScheduleState(Integer number, User user) {
        message.setStatus(StatusEnum.OK);
        Schedule schedule = findScedule(number,user);
        if (message.getStatus().equals(StatusEnum.NOT_FOUND)){
            message.setStatus(StatusEnum.NOT_FOUND);
            message.setMessage("작성자의 할일이 존재하지 않습니다 ");
            return message;
        }

        schedule.updateState(true);

        message.setStatus(StatusEnum.OK);
        message.setMessage(number+" 가 완료 상태로 변경되었습니다.");
        message.setData(new ScheduleResponseDto2(schedule, schedule.isState()));

        return message;

    }
}
