package com.sprata.my_schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {

    @NotNull(message = "필수 입니다")
    private String title;
    @NotNull(message = "필수 입니다")
    private String text;
    @NotNull(message = "필수 입니다")
    private String manager;
    @NotNull(message = "필수 입니다")
    private String pw;
    @NotNull(message = "필수 입니다")
    @FutureOrPresent(message = "현재,미래만 입력 가능합니다")
    private LocalDate date;
}
