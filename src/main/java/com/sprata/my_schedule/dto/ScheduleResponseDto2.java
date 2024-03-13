package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ScheduleResponseDto2 {
    private String title;
    private LocalDateTime createdAt;
    private String  username;
    private  Boolean state;
    public ScheduleResponseDto2(Schedule schedule, boolean state) {
        this.title = schedule.getTitle();
        this.createdAt  = schedule.getCreatedAt();
        this.username = schedule.getUser().getUsername();
        this.state = state;
    }
}
