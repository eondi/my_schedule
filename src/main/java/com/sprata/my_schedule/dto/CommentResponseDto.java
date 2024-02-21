package com.sprata.my_schedule.dto;

import com.sprata.my_schedule.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String text;
    public CommentResponseDto(Comment comment) {
        this.text = comment.getText();
    }
}
