package com.sprata.my_schedule.service;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import com.sprata.my_schedule.dto.ScheduleResponseDto;
import com.sprata.my_schedule.entity.Schedule;
import com.sprata.my_schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.save(new Schedule(requestDto));
        return new ScheduleResponseDto(schedule);
    }
}
