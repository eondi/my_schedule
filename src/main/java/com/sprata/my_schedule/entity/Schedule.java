package com.sprata.my_schedule.entity;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedules")
public class Schedule extends Timestamped{

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean state;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;



    public Schedule(ScheduleRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.text = requestDto.getText();
        this.user = user;
        this.state =false;
    }

    public Schedule(ScheduleRequestDto requestDto, User user, Integer number) {
        this.number = number;
        this.title = requestDto.getTitle();
        this.text = requestDto.getText();
        this.user = user;
        this.state =false;
        this.setCreatedAt(LocalDateTime.now());
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.text = requestDto.getText();
    }

    public void updateState(boolean state) {
        this.state = state;
    }
}
