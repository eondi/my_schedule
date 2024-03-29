package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Integer number;
    private String title;
    private String text;
    private LocalDateTime createdAt;
    private String  username;

    public ScheduleResponseDto(Schedule schedule) {
        this.number = schedule.getNumber();
        this.title = schedule.getTitle();
        this.text = schedule.getText();
        this.createdAt  = schedule.getCreatedAt();
        this.username = schedule.getUser().getUsername();
    }
}
