package com.sprata.my_schedule.entity;

import com.sprata.my_schedule.dto.CommentResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;



    public Comment(CommentResponseDto requestDto, User user, Schedule schedule) {
        this.text = requestDto.getText();
        this.user = user;
        this.schedule =schedule;
    }

    public void update(CommentResponseDto requestDto) {
        this.text = requestDto.getText();
    }
}