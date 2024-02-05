package com.sprata.my_schedule.entity;

import com.sprata.my_schedule.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

//    @Column(nullable = false)
//    private String username;

    @Column(nullable = false)
    private boolean state;
//
//    @ManyToMany
//    @JoinTable(name = "orders", // 중간 테이블 생성
//    joinColumns = @JoinColumn(name = "Schedule_id"),
//    inverseJoinColumns = @JoinColumn(name = "comment_id"))
//    private List<Comment> commentList = new ArrayList<>();
//
//    public void addCommentList(Comment comment) {
//        this.commentList.add(comment); // 외래 키(연관 관계) 설정
//        comment.getScheduleList().add(this);
//    }

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.text = requestDto.getText();
//        this.username = requestDto.getManager();
        this.state =false;


    }
}
