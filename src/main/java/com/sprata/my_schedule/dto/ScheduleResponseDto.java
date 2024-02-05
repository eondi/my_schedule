package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private Integer number;
    private String title;
    private String text;
    private LocalDateTime createdAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.number = schedule.getNumber();
        this.title = schedule.getTitle();
        this.text = schedule.getText();
        this.createdAt  = schedule.getCreatedAt();
    }
}
