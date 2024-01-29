package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private Integer number;
    private String title;
    private String text;
    private String manager;
    private LocalDate date;

    public ScheduleResponseDto(Schedule schedule) {
        this.number = schedule.getNumber();
        this.title = schedule.getTitle();
        this.text = schedule.getText();
        this.manager = schedule.getManager();
        this.date = schedule.getDate();
    }
}
