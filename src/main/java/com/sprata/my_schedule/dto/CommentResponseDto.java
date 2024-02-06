package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long number;
    private String text;
    public CommentResponseDto(Comment comment) {
        this.number = comment.getNumber();
        this.text = comment.getText();
    }
}
