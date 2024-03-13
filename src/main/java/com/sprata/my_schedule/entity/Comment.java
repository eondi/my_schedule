package com.sprata.my_schedule.entity;

import com.sprata.my_schedule.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;



    public Comment(CommentRequestDto requestDto, User user, Schedule schedule) {
        this.text = requestDto.getText();
        this.user = user;
        this.schedule =schedule;
    }
    public Comment(CommentRequestDto requestDto, User user, Schedule schedule, Long number) {
        this.text = requestDto.getText();
        this.user = user;
        this.schedule =schedule;
        this.number = number;
    }

    public void update(CommentRequestDto requestDto) {
        this.text = requestDto.getText();
    }
}
